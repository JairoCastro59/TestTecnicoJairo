package com.example.testtecnicojairo.splash.framework.usecase

import com.example.testtecnicojairo.splash.data.repository.SessionRepository
import com.example.testtecnicojairo.splash.domain.models.SessionResult
import javax.inject.Inject

class SessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): Result<SessionResult> {
        return sessionRepository.getSessionId()
    }
}