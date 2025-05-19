package com.interview.ivanjfbr.home.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.home.data.model.MoviesSectionRequest
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.data.network.MoviesCategoryUrl.NOW_PLAYING
import com.interview.ivanjfbr.home.data.network.MoviesCategoryUrl.POPULAR
import com.interview.ivanjfbr.home.data.network.MoviesCategoryUrl.TOP_RATED
import com.interview.ivanjfbr.home.data.network.MoviesCategoryUrl.UPCOMING
import com.interview.ivanjfbr.home.domain.interactor.GetMoviesSectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getMoviesSectionUseCase: GetMoviesSectionUseCase
) : ViewModel() {
    private val _nowPlayingState: MutableStateFlow<UiState<MoviesSectionResponse>> =
        MutableStateFlow(UiState.Loading)
    val nowPlayingState: StateFlow<UiState<MoviesSectionResponse>> get() = _nowPlayingState.asStateFlow()

    private val _popularState: MutableStateFlow<UiState<MoviesSectionResponse>> =
        MutableStateFlow(UiState.Loading)
    val popularState: StateFlow<UiState<MoviesSectionResponse>> get() = _popularState.asStateFlow()

    private val _topRatedState: MutableStateFlow<UiState<MoviesSectionResponse>> =
        MutableStateFlow(UiState.Loading)
    val topRatedState: StateFlow<UiState<MoviesSectionResponse>> get() = _topRatedState.asStateFlow()

    private val _upcomingState: MutableStateFlow<UiState<MoviesSectionResponse>> =
        MutableStateFlow(UiState.Loading)
    val upcomingState: StateFlow<UiState<MoviesSectionResponse>> get() = _upcomingState.asStateFlow()

    init {
        loadMovies(url = NOW_PLAYING, uiState = _nowPlayingState)
        loadMovies(url = POPULAR,  uiState = _popularState)
        loadMovies(url = TOP_RATED, uiState = _topRatedState)
        loadMovies(url = UPCOMING, uiState = _upcomingState)
    }

    private fun loadMovies(
        url: String,
        page: Int = 1,
        uiState: MutableStateFlow<UiState<MoviesSectionResponse>>
    ) {
        viewModelScope.launch {
            try {
                getMoviesSectionUseCase.execute(
                    MoviesSectionRequest(
                        url = url,
                        page = page
                    )
                )
                    .catch {
                        uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect { product ->
                        uiState.value = UiState.Success(product)
                    }
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message.toString())
            }
        }
    }
}