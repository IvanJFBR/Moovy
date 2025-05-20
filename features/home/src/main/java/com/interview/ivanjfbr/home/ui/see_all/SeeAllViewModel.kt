package com.interview.ivanjfbr.home.ui.see_all

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.domain.interactor.GetPagingMoviesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllViewModel @Inject constructor(
    private val getPagingMoviesListUseCase: GetPagingMoviesListUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _movies = MutableStateFlow<PagingData<MovieResponse>>(PagingData.empty())
    val movies: StateFlow<PagingData<MovieResponse>> = _movies.asStateFlow()

    init {
        savedStateHandle.get<String>("categoryUrl")?.let {
            loadMovies(it)
        }
    }

    private fun loadMovies(url: String) {
        viewModelScope.launch {
            getPagingMoviesListUseCase.execute(url)
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _movies.value = pagingData
                }
        }
    }
}