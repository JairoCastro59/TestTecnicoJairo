package com.example.testtecnicojairo.dashboard.data.repository

import com.example.testtecnicojairo.dashboard.data.dataSource.TopRatedMoviesDataSource
import javax.inject.Inject

class TopRatedMoviesRepository @Inject constructor(
    private val topRatedMoviesDataSource: TopRatedMoviesDataSource
) {
    suspend fun getTopRatedMovies() = topRatedMoviesDataSource.getTopRatedMovies()
}