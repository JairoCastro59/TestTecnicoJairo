package com.example.core.extensionFunctions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.AnyRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.example.core.base.fragment.BaseFragment
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.framework.presentation.dialog.FullScreenErrorDialog
import com.example.material.components.errorView.ErrorViewComponent
import com.example.material.model.Error


fun Fragment.back() {
    childFragmentManager.executePendingTransactions()
    activity?.onBackPressed()
}

inline fun <reified T : Activity> Fragment.navigateTo(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(activity, T::class.java)
    intent.init()
    startActivity(intent)
}

@SuppressWarnings("TooGenericExceptionCaught")
fun Fragment.navigateTo(
    @AnyRes destination: Int,
    arguments: Bundle? = null,
    handleException: Boolean = false,
    navOptions: NavOptions? = null
) {
    try {
        this.view?.findNavController()?.navigate(destination, arguments, navOptions)
    } catch (e: Exception) {
        if (handleException) {
            logError("")
        } else {
            throw e
        }
    }
}

fun Fragment.taskBeforeBack(unit: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                unit.invoke()
            }
        })
}

fun <VB : ViewBinding, VM : BaseViewModel> BaseFragment<VB, VM>.navigateToSection(
    path: String, bundle: Bundle? = null
) {
    try {
        navigateToSection(path, bundle)
    } catch (e: Exception){}
}

fun <VB : ViewBinding, VM : BaseViewModel> BaseFragment<VB, VM>.showGenericError(
    error: Error,
    actionFunction: (() -> Unit)? = null
) {
    FullScreenErrorDialog().apply {
        onRetryErrorListener = object : ErrorViewComponent.OnRetryActionErrorListener {
            override fun doOnRetryError(actionId: Int) {
                if (actionFunction != null) {
                    actionFunction.invoke()
                    return
                }
                //this@showGenericError.viewModel.onDefaultErrorAction(action)
            }
        }
        this.error = error
        closeImageCloseVisible = true
        onCloseErrorListener = object : ErrorViewComponent.OnCloseErrorListener {
            override fun doOnClose(actionId: Int) {
                dismiss()
                activity?.onBackPressed()
            }
        }
    }.also {
        it.show(childFragmentManager)
        childFragmentManager.executePendingTransactions()
        it.dialog?.setOnDismissListener {
            activity?.onBackPressed()
        }
    }
}

