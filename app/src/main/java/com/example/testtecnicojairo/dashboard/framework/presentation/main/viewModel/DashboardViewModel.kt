package com.example.testtecnicojairo.dashboard.framework.presentation.main.viewModel

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewModel.BaseViewModel
import com.example.testtecnicojairo.R
import com.example.testtecnicojairo.dashboard.constants.DashboardConstants
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.view.PermissionRequestType

class DashboardViewModel : BaseViewModel() {

    private val _navigationEventMLD = MutableLiveData<Pair<Int, Bundle>>()
    val navigationEvent get() = _navigationEventMLD as LiveData<Pair<Int, Bundle>>

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
    }

    fun navigateToRequestPermission(permissionRequestType: PermissionRequestType) {
        _navigationEventMLD.value =
            Pair(
                R.id.action_mapFragment_to_permissionRequestFragment,
                bundleOf(
                    DashboardConstants.TYPE_PERMISSION to permissionRequestType
                )
            )
    }
}