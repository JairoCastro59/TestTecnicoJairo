package com.example.testtecnicojairo.splash.framework.data.config.retrofit.request

import com.google.gson.annotations.SerializedName

data class SessionRequest(
    @SerializedName("access_token")
    val access_token: String
)
