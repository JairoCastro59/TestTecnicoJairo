package com.example.testtecnicojairo.dashboard.domain.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val poster_path: String? = null,
    val overview: String? = null,
    val release_date: String? = null,
    val adult: Boolean? = null,
    val id: Int? = null,
    val original_title: String? = null,
    val original_language: String? = null,
    val title: String? = null,
    val backdrop_path: String? = null,
    val popularity: Float? = null,
    val vote_count: Int? = null,
    val video: Boolean? = null,
    val vote_average: Float? = null
) : Parcelable
