package com.example.testtecnicojairo.splash.view

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import com.example.core.base.activity.BaseActivity
import com.example.testtecnicojairo.databinding.ActivitySplashBinding
import com.example.testtecnicojairo.splash.viewModel.SplashViewModel
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.dashboard.framework.presentation.main.view.DashboardActivity
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override val binding: ActivitySplashBinding by viewBinding(ActivitySplashBinding::inflate)

    override fun initView() {
        super.initView()
        binding.splashAnim.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator?) {
            }

            override fun onAnimationEnd(p0: Animator?) {
                viewModel.navigateToDasboardScreen()
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
    }

    private fun onNavigateToDashboard() {
        startActivity(
            Intent(this, DashboardActivity::class.java)
        )
        finishAfterTransition()
    }
}