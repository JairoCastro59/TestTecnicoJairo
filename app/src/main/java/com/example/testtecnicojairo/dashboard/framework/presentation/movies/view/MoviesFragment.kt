package com.example.testtecnicojairo.dashboard.framework.presentation.main.movies.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.core.base.fragment.BaseFragment
import com.example.material.delegates.viewBinding
import com.example.testtecnicojairo.dashboard.framework.presentation.movies.viewModel.MovieViewModel
import com.example.testtecnicojairo.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MovieViewModel>() {

    override val binding: FragmentMoviesBinding by viewBinding {
        FragmentMoviesBinding.inflate(layoutInflater)
    }

    override val viewModel: MovieViewModel by viewModels()

    override fun setUp(arguments: Bundle?) {
        super.setUp(arguments)
        viewModel.setUp(arguments)
    }
}