package com.interview.ivanjfbr.home.ui.dashboard.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interview.ivanjfbr.core.ui.MoovyDefaultTheme
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.ui.dashboard.DashboardViewModel

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    MoovyDefaultTheme {
        RenderMoviesSectionList(
            uiState = viewModel.nowPlayingState.collectAsStateWithLifecycle().value
        )
    }
}

@Composable
fun RenderMoviesSectionList(uiState: UiState<MoviesSectionResponse>) {
    when (uiState) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyRow(Modifier.padding(top = 10.dp)) {
                items(
                    items = uiState.data.results ?: emptyList(),
                    key = { item ->
                        item.movieId.toString()
                    }
                ) { item ->
                    Text(text = "asd")
                }
            }
        }

        is UiState.Error -> {}
    }
}