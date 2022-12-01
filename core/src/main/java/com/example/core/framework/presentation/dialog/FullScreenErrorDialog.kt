package com.example.core.framework.presentation.dialog

import android.graphics.Color
import androidx.fragment.app.viewModels
import com.example.material.R as MaterialResource
import com.example.core.base.dialog.BaseDialog
import com.example.core.base.viewModel.EmptyViewModel
import com.example.core.databinding.DialogGenericErrorBinding
import com.example.material.delegates.viewBinding
import com.example.material.model.Error
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FullScreenErrorDialog @Inject constructor(private val errorSplash: Error) :
    BaseDialog<DialogGenericErrorBinding, EmptyViewModel>(
        dialogStyle = MaterialResource.style.NoActionBarTranslucent,
        dialogWindowAnimation = MaterialResource.style.DialogAnimationFade,
        statusBarColorInt = Color.WHITE
    ) {

    override val binding: DialogGenericErrorBinding by viewBinding(DialogGenericErrorBinding::inflate)

    override val viewModel: EmptyViewModel by viewModels()

    var closeImageCloseVisible: Boolean = false

    override fun initView() {
        super.initView()
        dialog?.setCancelable(closeImageCloseVisible)
        binding.apply {
            alertImageView.setImageResource(errorSplash.icon)
            titleOutputText.text = errorSplash.title
            messageOutputText.text = errorSplash.message
            closeImageView.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }
}