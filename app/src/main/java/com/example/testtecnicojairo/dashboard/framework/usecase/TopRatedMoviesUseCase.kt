package com.example.testtecnicojairo.dashboard.framework.usecase

import com.example.testtecnicojairo.dashboard.data.repository.TopRatedMoviesRepository
import com.example.testtecnicojairo.dashboard.domain.models.TopRatedMoviesResult
import javax.inject.Inject

class TopRatedMoviesUseCase @Inject constructor(
    private val topRatedMoviesRepository: TopRatedMoviesRepository
) {
    suspend operator fun invoke(): Result<TopRatedMoviesResult>{
        return topRatedMoviesRepository.getTopRatedMovies()
    }
}