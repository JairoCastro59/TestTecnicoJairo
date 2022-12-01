package com.example.testtecnicojairo.splash.data.repository

import com.example.testtecnicojairo.splash.data.dataSource.SessionDataSource
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val sessionDataSource: SessionDataSource
) {
    suspend fun getSessionId() = sessionDataSource.getSessionId()
}