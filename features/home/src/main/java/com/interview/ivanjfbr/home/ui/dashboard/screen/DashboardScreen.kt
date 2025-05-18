package com.interview.ivanjfbr.home.ui.dashboard.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interview.ivanjfbr.core.ui.MoovyDefaultTheme
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.home.R
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.ui.dashboard.DashboardViewModel
import com.interview.ivanjfbr.home.ui.dashboard.components.MovieCard

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel()
) {
    MoovyDefaultTheme {
        LazyColumn {
            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.now_playing) ,
                    uiState = viewModel.nowPlayingState.collectAsStateWithLifecycle().value
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.popular) ,
                    uiState = viewModel.popularState.collectAsStateWithLifecycle().value
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.top_rated) ,
                    uiState = viewModel.topRatedState.collectAsStateWithLifecycle().value
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.upcoming) ,
                    uiState = viewModel.upcomingState.collectAsStateWithLifecycle().value
                )
            }
        }
    }
}

@Composable
fun RenderMoviesSectionList(
    sectionTitle: String,
    uiState: UiState<MoviesSectionResponse>) {
    when (uiState) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            Column {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = sectionTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )

                LazyRow(Modifier.padding(top = 10.dp)) {
                    items(
                        items = uiState.data.results ?: emptyList(),
                        key = { item ->
                            item.movieId.toString()
                        }
                    ) { item ->
                        MovieCard(
                            movieImageUrl = item.posterPath,
                            contentDescription = item.title ?: "",
                            movieTitle = item.title ?: ""
                        )
                    }
                }
            }
        }

        is UiState.Error -> {}
    }
}