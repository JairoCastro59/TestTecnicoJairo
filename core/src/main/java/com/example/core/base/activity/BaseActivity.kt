package com.example.core.base.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.core.base.viewModel.BaseViewModel
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {

    /**
     * This ViewModel will allow us to keep the logic separate from our UI.
     *
     * Example of implementation:
     *  override val viewModel: EmptyViewModel by viewModels()
     */
    abstract val viewModel: VM

    /**
     * This ViewBinding will allow us to create our View
     *
     * Example of implementation:
     *  override val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
     *
     *  or
     *
     *  override val binding by viewBinding(ActivityMainBinding::inflate)
     */
    abstract val binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        addViewModelObservables()
        setUp(intent.extras)
    }

    override fun attachBaseContext(context: Context) {
        run {
            super.attachBaseContext(context)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
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
    protected open fun setUp(extras: Bundle?) = Unit

    /**
     * This method allow us to add observers to our ViewModel events
     * Example:
     *  override fun addViewModelObservables() = with(baseViewModel) {
     *      observe(foo1()) { someAction1() }
     *      observe(foo2()) { someAction2() }
     *  }
     */
    protected open fun addViewModelObservables() = Unit
}