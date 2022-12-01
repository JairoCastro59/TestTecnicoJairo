package com.example.testtecnicojairo.splash.framework.data.implementation

import com.example.testtecnicojairo.dashboard.constants.DashboardConstants
import com.example.testtecnicojairo.splash.data.dataSource.SessionDataSource
import com.example.testtecnicojairo.splash.domain.models.SessionResult
import com.example.testtecnicojairo.splash.framework.data.config.retrofit.SessionApiService
import com.example.testtecnicojairo.splash.framework.data.config.retrofit.request.SessionRequest
import com.example.testtecnicojairo.splash.framework.data.implementation.mappers.SessionMapper
import javax.inject.Inject

class SessionDataSourceImpl @Inject constructor(
    private val sessionApiService: SessionApiService,
    private val sessionMapper: SessionMapper
): SessionDataSource {

    override suspend fun getSessionId(): Result<SessionResult> {
        return try {
            val request = SessionRequest(access_token = DashboardConstants.ACCESS_TOKEN)
            val result = sessionApiService.getSessionId(request).await()
            result.let {
                Result.success(sessionMapper.map(it))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}