package com.example.testtecnicojairo.splash.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.core.base.viewModel.BaseViewModel

class SplashViewModel : BaseViewModel() {

    private val _navigateToDashboard = MutableLiveData<Unit>()
    val navigateToDashboard get() = _navigateToDashboard

    fun navigateToDasboardScreen() {
        _navigateToDashboard.value = Unit
    }
}