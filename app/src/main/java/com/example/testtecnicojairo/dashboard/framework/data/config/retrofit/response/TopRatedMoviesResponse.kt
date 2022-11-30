package com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.response

import com.google.gson.annotations.SerializedName

data class TopRatedMoviesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: ArrayList<Movie>
)
