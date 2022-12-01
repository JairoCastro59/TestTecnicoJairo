package com.example.testtecnicojairo.dashboard.framework.presentation.movies.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.base.fragment.BaseFragment
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.dashboard.domain.models.Movie
import com.example.testtecnicojairo.dashboard.framework.presentation.movies.view.adapter.MoviesAdapter
import com.example.testtecnicojairo.dashboard.framework.presentation.movies.viewModel.MovieViewModel
import com.example.testtecnicojairo.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MovieViewModel>() {

    override val binding: FragmentMoviesBinding by viewBinding {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    override val viewModel: MovieViewModel by viewModels()

    private var moviesAdapter: MoviesAdapter? = null

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        viewModel.setUp(arguments)
    }

    override fun initView() {
        super.initView()
        binding.apply {
            moviesList.setHasFixedSize(true)
            moviesList.layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL,
                false
            )
            moviesAdapter = MoviesAdapter()
            moviesList.adapter =  moviesAdapter
        }
    }

    override fun addViewModelObservables() = with(viewModel) {
        getTopRatedMovies.observe(this@MoviesFragment) {
            setMovieList(it.data?.results ?: arrayListOf())
        }
    }

    private fun setMovieList(movies: ArrayList<Movie>) {
        moviesAdapter?.changeDataSet(movies)
    }
}