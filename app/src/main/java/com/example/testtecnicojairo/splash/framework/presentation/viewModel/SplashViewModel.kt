package com.example.testtecnicojairo.splash.framework.presentation.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.core.extensionFunctions.logInfo
import com.example.testtecnicojairo.db.repository.SessionDBRepository
import com.example.testtecnicojairo.db.transformer.Transformer
import com.example.testtecnicojairo.splash.domain.models.SessionResult
import com.example.testtecnicojairo.splash.framework.usecase.SessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionUseCase: SessionUseCase,
    private val sessionDBRepository: SessionDBRepository
) : BaseViewModel() {

    private var animationEnd = false
    private var getSession = false

    private val _navigateToDashboard = MutableLiveData<Unit>()
    val navigateToDashboard get() = _navigateToDashboard

    private val _showGenericError = MutableLiveData<String>()
    val showGenericError get() = _showGenericError

    fun navigateToDasboardScreen() {
        if (animationEnd && getSession)
            _navigateToDashboard.value = Unit
    }

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        sessionDBRepository.getAllSession().let { _sessionEntity ->
            val sessionTemp = _sessionEntity.value?.let {
                Transformer.converterSessionEntitytoSession(
                    it
                )
            }
            sessionTemp?.session_id?.let {
                getSession = true
                navigateToDasboardScreen()
                logInfo(it)
            } ?: getSessionId()
        }
    }

    fun getSessionId(){
        viewModelScope.launch {
            sessionUseCase.invoke().let { _sessionResult ->
                if(_sessionResult.isSuccess) {
                    _sessionResult.getOrNull()?.let { insertSessionId(it) }
                } else {
                    showGenericError.value = _sessionResult.exceptionOrNull()?.toString()
                }
            }
        }
    }

    fun insertSessionId(session: SessionResult) {
        viewModelScope.launch {
            sessionDBRepository.insertSession(session)
            getSession = true
        }
    }

    fun animationEnd() {
        animationEnd = true
        navigateToDasboardScreen()
    }
}