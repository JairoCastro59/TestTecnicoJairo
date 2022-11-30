package com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.response

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("original_language")
    val original_language: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("popularity")
    val popularity: Float,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Float
)
