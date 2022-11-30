
package com.example.material.components.errorView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.example.material.R
import com.example.material.databinding.ViewServiceErrorBinding
import com.example.material.delegates.viewBinding
import com.example.material.extensionFunctions.setVisibility
import com.example.material.model.Error

class ErrorViewComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var error: Error? = null

    private val binding: ViewServiceErrorBinding by lazy {
        viewBinding(ViewServiceErrorBinding::inflate, true)
    }

    interface OnRetryActionErrorListener {
        fun doOnRetryError(actionId: Int)
    }

    interface OnCloseErrorListener {
        fun doOnClose(actionId: Int)
    }

    private var onRetryActionErrorListener: OnRetryActionErrorListener? = null
    private var onCloseErrorListener: OnCloseErrorListener? = null

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.view_service_error, this, true)
        val typedArray =
            getContext().obtainStyledAttributes(attrs, R.styleable.ErrorViewComponent)
        binding.closeImageView.setVisibility(
            typedArray.getBoolean(
                R.styleable.ErrorViewComponent_widthCloseButton,
                false
            )
        )
        typedArray.recycle()
        binding.retry.setOnClickListener(this)
        binding.closeImageView.setOnClickListener(this)
    }

    fun setOnRetryActionErrorListener(onRetryActionErrorListener: OnRetryActionErrorListener) {
        this.onRetryActionErrorListener = onRetryActionErrorListener
    }

    fun setOnCloseErrorListener(onCloseErrorListener: OnCloseErrorListener) {
        this.onCloseErrorListener = onCloseErrorListener
    }

    fun setImageCloseVisibility(visible: Boolean) {
        binding.closeImageView.setVisibility(visible)
    }

    fun setError(error: Error) {
        this.error = error

        binding.alertImageView.setImageResource(error.icon)

        binding.titleOutputText.text =
            if (error.titleRes != null)
                ""
            else
                error.title?:""

        binding.messageOutputText.text =  if (error.messageRes != null)
            ""
        else
            error.message?:""

        binding.retry.text = if(error.action?.actionName != null)
            ""
        else
            error.action?.actionString?:""
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.retry-> error?.action?.actionId?.let {
                onRetryActionErrorListener?.doOnRetryError(
                    it
                )
            }
            R.id.closeImageView -> error?.action?.actionId?.let {
                onCloseErrorListener?.doOnClose(
                    it
                )
            }
        }
    }
}