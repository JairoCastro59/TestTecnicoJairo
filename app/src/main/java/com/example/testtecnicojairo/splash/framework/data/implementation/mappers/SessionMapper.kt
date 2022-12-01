package com.example.testtecnicojairo.splash.framework.data.implementation.mappers

import com.example.testtecnicojairo.base.Mapper
import com.example.testtecnicojairo.splash.domain.models.SessionResult
import com.example.testtecnicojairo.splash.framework.data.config.retrofit.response.SessionResponse
import javax.inject.Inject

class SessionMapper @Inject constructor() :
    Mapper<SessionResponse, SessionResult>{

    override fun map(input: SessionResponse): SessionResult {
        return SessionResult(
            success = input.success,
            session_id = input.session_id
        )
    }
}