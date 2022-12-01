package com.example.testtecnicojairo.splash.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SessionResult(
    val success: Boolean?,
    val session_id: String?
) : Parcelable
