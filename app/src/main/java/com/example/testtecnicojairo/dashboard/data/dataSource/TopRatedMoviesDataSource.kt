package com.example.testtecnicojairo.dashboard.data.dataSource

import com.example.testtecnicojairo.dashboard.domain.models.TopRatedMoviesResult

interface TopRatedMoviesDataSource {
    suspend fun getTopRatedMovies(): Result<TopRatedMoviesResult>
}