package com.example.core.framework.presentation.dialog

import android.graphics.Color
import androidx.fragment.app.viewModels
import com.example.material.R as MaterialResource
import com.example.core.base.dialog.BaseDialog
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.databinding.DialogGenericErrorBinding
import com.example.material.components.errorView.ErrorViewComponent
import com.example.material.delegates.viewBinding
import com.example.material.model.Error
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FullScreenErrorDialog @Inject constructor() :
    BaseDialog<DialogGenericErrorBinding, EmptyViewModel>(
        dialogStyle = MaterialResource.style.NoActionBarTranslucent,
        dialogWindowAnimation = MaterialResource.style.DialogAnimationFade,
        statusBarColorInt = Color.WHITE
    ),
    ErrorViewComponent.OnRetryActionErrorListener,
    ErrorViewComponent.OnCloseErrorListener {

    override val binding: DialogGenericErrorBinding by viewBinding(DialogGenericErrorBinding::inflate)

    override val viewModel: EmptyViewModel by viewModels()

    var onRetryErrorListener: ErrorViewComponent.OnRetryActionErrorListener? = null
    var onCloseErrorListener: ErrorViewComponent.OnCloseErrorListener? = null
    var closeImageCloseVisible: Boolean = false

    var error: Error ? = null

    override fun initView() {
        super.initView()
        with(binding.genericErrorComponent) {
            setOnRetryActionErrorListener(this@FullScreenErrorDialog)
            setOnCloseErrorListener(this@FullScreenErrorDialog)
            setImageCloseVisibility(closeImageCloseVisible)
            dialog?.setCancelable(closeImageCloseVisible)
        }
        error?.let {
            binding.genericErrorComponent.setError(it)
        }
    }

    override fun doOnRetryError(actionId: Int) {
        onRetryErrorListener?.doOnRetryError(actionId)
        dismiss()
    }

    override fun doOnClose(actionId: Int) {
        onCloseErrorListener?.doOnClose(actionId)
    }

}