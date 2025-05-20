package com.interview.ivanjfbr.home.ui.movie_detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.interview.ivanjfbr.core.ui.MoovyDefaultTheme
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.core.ui.components.ErrorView
import com.interview.ivanjfbr.core.ui.components.LoadingView
import com.interview.ivanjfbr.core.ui.components.MoovyTopBar
import com.interview.ivanjfbr.core.ui.getTMDBImageRequest
import com.interview.ivanjfbr.home.data.model.MovieResponse
import com.interview.ivanjfbr.home.ui.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    navController: NavController,
    title: String,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    MoovyDefaultTheme(
        topBar = {
            MoovyTopBar(
                title = title,
                onBack = { navController.popBackStack() }
            )
        }
    ) {
        when (val uiState = viewModel.state.collectAsState().value) {
            is UiState.Error -> {
                ErrorView()
            }
            is UiState.Loading -> {
                LoadingView()
            }
            is UiState.Success -> {
                MovieDetailContent(uiState.data)
            }
        }
    }
}

@Composable
private fun MovieDetailContent(movie: MovieResponse) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 24.dp)
    ) {
        item {
            movie.backdropPath?.let {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    painter = rememberAsyncImagePainter(
                        model = getTMDBImageRequest(
                            imageUrl = it
                        )
                    ),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = movie.title
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = movie.title ?: "",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                movie.genres?.forEach { genre ->
                    Text(
                        text = if (genre == movie.genres.last()) genre?.name.toString() else genre?.name + ", ",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Overview",
                style = MaterialTheme.typography.headlineMedium,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = movie.overview ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}