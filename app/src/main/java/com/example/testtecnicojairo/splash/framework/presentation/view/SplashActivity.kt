package com.example.testtecnicojairo.splash.framework.presentation.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.core.base.activity.BaseActivity
import com.example.core.framework.presentation.dialog.FullScreenErrorDialog
import com.example.material.components.errorView.ErrorViewComponent
import com.example.testtecnicojairo.databinding.ActivitySplashBinding
import com.example.testtecnicojairo.splash.framework.presentation.viewModel.SplashViewModel
import com.example.material.delegates.viewBinding
import com.example.material.model.Error
import com.example.testtecnicojairo.dashboard.framework.presentation.main.view.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(),
    ErrorViewComponent.OnRetryActionErrorListener{

    private var errorDialog: FullScreenErrorDialog? = null

    override val viewModel: SplashViewModel by viewModels()

    override val binding: ActivitySplashBinding by viewBinding(ActivitySplashBinding::inflate)

    override fun setUp(extras: Bundle?) {
        super.setUp(extras)
        viewModel.setUp(extras)
    }

    override fun initView() {
        super.initView()
        binding.splashAnim.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                viewModel.animationEnd()
            }

            override fun onAnimationCancel(p0: Animator?) {
            }

            override fun onAnimationRepeat(p0: Animator?) {
            }
        })
    }

    override fun addViewModelObservables() = with(viewModel) {
        navigateToDashboard.observe(this@SplashActivity) {
            onNavigateToDashboard()
        }
        showGenericError.observe(this@SplashActivity) {
            showGenericError(it)
        }
    }

    private fun onNavigateToDashboard() {
        startActivity(
            Intent(this, DashboardActivity::class.java)
        )
        finishAfterTransition()
    }

    private fun showGenericError(messageStr: String) {
        val errorSplash = Error(
            messageRes = null,
            titleRes = null,
            message = messageStr,
            title = "Algo salio mal"
        )
        errorDialog = FullScreenErrorDialog(errorSplash)
        errorDialog?.show(this.supportFragmentManager)
    }

    override fun doOnRetryError(actionId: Int) {

    }
}