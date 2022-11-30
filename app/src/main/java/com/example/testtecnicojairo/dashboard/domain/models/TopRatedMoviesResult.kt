package com.example.testtecnicojairo.dashboard.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopRatedMoviesResult(
    val page: Int,
    val results: ArrayList<Movie>
) : Parcelable
