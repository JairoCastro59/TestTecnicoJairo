package com.example.testtecnicojairo.dashboard.framework.data.config.retrofit

import com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.response.TopRatedMoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface DashboardApiService {

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(): Deferred<TopRatedMoviesResponse>

}