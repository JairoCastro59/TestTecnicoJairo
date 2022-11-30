package com.example.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.core.base.viewModel.BaseViewModel

abstract class BaseFragment <VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    /**
     * This ViewBinding will allow us to create our View
     *
     * Example of implementation:
     * override val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::inflate)
     */
    abstract val binding: VB

    /**
     * This ViewModel will allow us to keep the logic separate from our UI.
     *
     * Example of implementation:
     * override val viewModel: MainViewModel by viewModels()
     */
    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUp(arguments)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addViewModelObservables()
    }

    /**
     * Override it to be able to configure the view together with the associated ViewBinding.
     * Example:
     *  override fun initView() = with(binding) {
     *      someTextView.text = "Binding!!"
     *  }
     */
    protected open fun initView() = Unit

    /**
     * Override it to be able to initialize the logic with received Bundle data
     */
    protected open fun setUp(arguments: Bundle?) = Unit

    /**
     * This method allow us to add observers to our ViewModel events
     * Example:
     *  override fun addViewModelObservables() = with(baseViewModel) {
     *      observe(foo1()) { someAction1() }
     *      observe(foo2()) { someAction2() }
     *  }
     */
    protected open fun addViewModelObservables() = Unit

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}
