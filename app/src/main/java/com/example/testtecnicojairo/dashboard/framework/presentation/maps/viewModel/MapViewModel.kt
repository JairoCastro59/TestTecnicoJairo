package com.example.testtecnicojairo.dashboard.framework.presentation.maps.viewModel

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewModel.BaseViewModel
import com.example.testtecnicojairo.dashboard.framework.presentation.maps.data.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(application: Application) : BaseViewModel() {

    private val locationRepository = LocationRepository.getInstance(
        application.applicationContext,
        Executors.newSingleThreadExecutor()
    )

    private val _receivingLocationUpdates = MutableLiveData<Boolean>()
    val receivingLocationUpdates get() = _receivingLocationUpdates

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        startLocationUpdates()
    }

    fun startLocationUpdates() = locationRepository.startLocationUpdates()

    fun stopLocationUpdates() = locationRepository.stopLocationUpdates()
}