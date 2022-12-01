package com.example.testtecnicojairo.splash.data.dataSource

import com.example.testtecnicojairo.splash.domain.models.SessionResult

interface SessionDataSource {
    suspend fun getSessionId(): Result<SessionResult>
}