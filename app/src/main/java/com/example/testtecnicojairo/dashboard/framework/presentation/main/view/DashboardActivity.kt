package com.example.testtecnicojairo.dashboard.framework.presentation.main.view


import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.core.base.activity.BaseActivity
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.R
import com.example.testtecnicojairo.dashboard.framework.presentation.main.viewModel.DashboardViewModel
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.utils.Callbacks
import com.example.testtecnicojairo.databinding.ActivityDashboardBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(),
    Callbacks{

    private lateinit var navController: NavController

    override val viewModel: DashboardViewModel by viewModels()

    override val binding: ActivityDashboardBinding by viewBinding(ActivityDashboardBinding::inflate)

    override fun setUp(extras: Bundle?) {
        viewModel.setUp(extras)
    }

    override fun initView() {
        super.initView()
        navController = findNavController(R.id.fragment)
        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
        }
    }

    override fun addViewModelObservables() = with(viewModel) {
        navigationEvent.observe(this@DashboardActivity) {
            try {
                navController.navigate(it.first, it.second, null)
            } catch (e: Exception) {
                    throw e
            }
        }
    }

    override fun requestFineLocationPermission() {
        //viewModel.navigateToRequestPermission(PermissionRequestType.FINE_LOCATION)
    }

    override fun requestBackgroundLocationPermission() {
        //viewModel.navigateToRequestPermission(PermissionRequestType.FINE_LOCATION)
    }
}