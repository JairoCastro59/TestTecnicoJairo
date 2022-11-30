package com.example.testtecnicojairo.dashboard.framework.presentation.movies.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.base.viewModel.BaseViewModel
import com.example.jairopruebatecnica.dashboard.domain.models.Result
import com.example.testtecnicojairo.dashboard.domain.models.TopRatedMoviesResult
import com.example.testtecnicojairo.dashboard.framework.usecase.TopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor (
    private val topRatedMoviesUseCase: TopRatedMoviesUseCase
) : BaseViewModel() {

    private val _getTopRatedMovies = MutableLiveData<Result<TopRatedMoviesResult?>>()
    val getTopRatedMovies get() = _getTopRatedMovies

    override fun setUp(bundle: Bundle?) {
        super.setUp(bundle)
        getTopRatedMovies()
    }

    fun getTopRatedMovies() = viewModelScope.launch {
        topRatedMoviesUseCase.invoke().let { _topRatedMovies ->
            if (_topRatedMovies.isSuccess) {
                _getTopRatedMovies.value = Result.success(_topRatedMovies.getOrNull())
            }
        }
    }
}