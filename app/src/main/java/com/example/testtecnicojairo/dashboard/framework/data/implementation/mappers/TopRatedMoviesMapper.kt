package com.example.testtecnicojairo.dashboard.framework.data.implementation.mappers

import com.example.testtecnicojairo.base.Mapper
import com.example.testtecnicojairo.dashboard.domain.models.Movie
import com.example.testtecnicojairo.dashboard.domain.models.TopRatedMoviesResult
import com.example.testtecnicojairo.dashboard.framework.data.config.retrofit.response.TopRatedMoviesResponse
import javax.inject.Inject

class TopRatedMoviesMapper @Inject constructor() :
    Mapper<TopRatedMoviesResponse, TopRatedMoviesResult> {

    override fun map(input: TopRatedMoviesResponse): TopRatedMoviesResult {
        val topRatedMovies = arrayListOf<Movie>()
        input.results.forEach { _movie ->
            topRatedMovies.add(
                Movie(
                    id = _movie.id,
                    title = _movie.title,
                    poster_path = _movie.poster_path,
                    overview = _movie.overview,
                    release_date = _movie.release_date,
                    adult = _movie.adult,
                    original_language = _movie.original_language,
                    original_title = _movie.original_title,
                    backdrop_path = _movie.backdrop_path,
                    popularity = _movie.popularity,
                    vote_average = _movie.vote_average,
                    vote_count = _movie.vote_count,
                    video = _movie.video
                )
            )
        }
        return TopRatedMoviesResult(
            page = input.page,
            results = topRatedMovies
        )
    }

}