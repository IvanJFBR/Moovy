package com.interview.ivanjfbr.home.ui.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.domain.interactor.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state: MutableStateFlow<UiState<MovieResponse>> =
        MutableStateFlow(UiState.Loading)
    val state: StateFlow<UiState<MovieResponse>> get() = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("movieId")?.let {
            loadMovieDetail(it.toString())
        }
    }

    private fun loadMovieDetail(movieID: String) {
        viewModelScope.launch {
            try {
                getMovieDetailsUseCase.execute(movieID)
                    .catch {
                        _state.value = UiState.Error(it.message.toString())
                    }
                    .collect { product ->
                        _state.value = UiState.Success(product)
                    }
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message.toString())
            }
        }
    }
}