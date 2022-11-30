package com.example.testtecnicojairo.dashboard.framework.data.implementation

import com.example.testtecnicojairo.dashboard.data.dataSource.TopRatedMoviesDataSource
import com.example.testtecnicojairo.dashboard.domain.models.TopRatedMoviesResult
import com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.DashboardApiService
import com.example.testtecnicojairo.dashboard.framework.data.implementation.mappers.TopRatedMoviesMapper
import javax.inject.Inject

class TopRatedMoviesDataSourceImpl @Inject constructor(
    private val dashboardApiService: DashboardApiService,
    private val topRatedMoviesMapper: TopRatedMoviesMapper
): TopRatedMoviesDataSource{
    override suspend fun getTopRatedMovies(): Result<TopRatedMoviesResult> {
        return try {
            val result = dashboardApiService.getTopRatedMovies().await()
            result.let {
                Result.success(topRatedMoviesMapper.map(it))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}