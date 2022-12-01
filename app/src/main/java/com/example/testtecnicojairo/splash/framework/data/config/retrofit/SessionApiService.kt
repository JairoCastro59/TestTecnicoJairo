package com.example.testtecnicojairo.splash.framework.data.config.retrofit

import com.example.testtecnicojairo.splash.framework.data.config.retrofit.request.SessionRequest
import com.example.testtecnicojairo.splash.framework.data.config.retrofit.response.SessionResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface SessionApiService {

    @POST("/3/authentication/session/convert/4")
    fun getSessionId(@Body request: SessionRequest): Deferred<SessionResponse>
}