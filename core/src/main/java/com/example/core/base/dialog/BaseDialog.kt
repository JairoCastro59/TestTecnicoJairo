package com.example.core.base.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.Nullable
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logError

abstract class BaseDialog<VB : ViewBinding, VM : BaseViewModel> constructor(
    @StyleRes private val dialogStyle: Int,
    @StyleRes private val dialogWindowAnimation: Int,
    private val statusBarColorInt: Int
) : AppCompatDialogFragment() {

    /**
     * This ViewBinding will allow us to create our View
     *
     * Example of implementation:
     * override val binding: MainFragmentBinding by viewBinding(MainDialogFragmentBinding::inflate)
     */
    abstract val binding: VB

    /**
     * This ViewModel will allow us to keep the logic separate from our UI.
     *
     * Example of implementation:
     * override val viewModel: MainDialogViewModel by viewModels()
     */
    abstract val viewModel: VM

    /**
     * Tag to identify dialog in fragment manager
     */
    protected val dialogTag: String by lazy { this::class.simpleName!! }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, dialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = binding.root
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = dialogWindowAnimation
            setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            statusBarColor = statusBarColorInt
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addViewModelObservables()
        initView()
        setUp(arguments)
    }

    /**
     * Override it to be able to initialize the logic with received Bundle data
     */
    protected open fun setUp(arguments: Bundle?) {
        viewModel.setUp(arguments)
    }

    /**
     * This method allow us to add observers to our ViewModel events
     * Example:
     *  override fun addViewModelObservables() = with(baseViewModel) {
     *      observe(foo1()) { someAction1() }
     *      observe(foo2()) { someAction2() }
     *  }
     */
    protected open fun addViewModelObservables() = Unit

    /**
     * Override it to be able to configure the view together with the associated ViewBinding.
     * Example:
     *  override fun initView() = with(binding) {
     *      someTextView.text = "Binding!!"
     *  }
     */
    protected open fun initView() = Unit

    fun show(fragmentManager: FragmentManager) {
        // It added isVisible because it already is visible does not need to show again the dialog and avoid crash.
        if (!isVisible && !isAdded) {
            /*
                This line add to avoid if state of fragment is destroyed and state loss and generate
                IllegalStateException that means FragmentTransaction and commit is call alter onSaveInstanceState
             */
            if (!fragmentManager.isDestroyed && !fragmentManager.isStateSaved) {
                val fragment = fragmentManager.findFragmentByTag(dialogTag)
                if (fragment == null) {
                    show(fragmentManager, dialogTag)
                }
            }
        }
    }

    @SuppressWarnings("ForbiddenComment", "TooGenericExceptionCaught")
    override fun dismiss() {
        // It added isAdded to avoid crash that try to dismiss dialog that does not has added
        if (isVisible && isAdded) {
            // To fix the crash: /issues/0e64d32ee4d0eb8d19af03e7544022d0
            // TODO: Remove this try/catch when Dexter library will be replaced in the project
            try {
                super.dismiss()
            } catch (e: Exception) {
                logError(e.message)
            }
        }
    }
}