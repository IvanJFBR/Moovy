package com.interview.ivanjfbr.home.ui.see_all.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.interview.ivanjfbr.core.ui.MoovyDefaultTheme
import com.interview.ivanjfbr.core.ui.components.HandlePagingResult
import com.interview.ivanjfbr.core.ui.components.MoovyTopBar
import com.interview.ivanjfbr.core.ui.components.MovieCard
import com.interview.ivanjfbr.core.ui.components.PaginationProgress
import com.interview.ivanjfbr.home.ui.navigation.models.Screens
import com.interview.ivanjfbr.home.ui.see_all.SeeAllViewModel

@Composable
fun SeeAllScreen(
    navController: NavController,
    title: String,
    viewModel: SeeAllViewModel = hiltViewModel()
) {
    MoovyDefaultTheme(
        topBar = {
            MoovyTopBar(
                title = title,
                onBack = { navController.popBackStack() }
            )
        }
    ) {
        Box(modifier = Modifier.padding(bottom = 20.dp)) {
            val movieItems = viewModel.movies.collectAsLazyPagingItems()
            val modifier =
                if (movieItems.loadState.append == LoadState.Loading)
                    Modifier.padding(bottom = 80.dp)
                else Modifier.fillMaxSize()
            LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(128.dp), content = {
                items(movieItems.itemCount) { i ->
                    Row {
                        movieItems[i]?.let { item ->
                            MovieCard(
                                movieImageUrl = item.posterPath,
                                contentDescription = item.title ?: "",
                                movieTitle = item.title ?: "",
                                onClick = {
                                    navController.navigate(
                                        route = Screens.MovieDetailScreen.route + "?movieId=${item.movieId.toString()}&moviesTitle=${item.title}"
                                    )
                                }
                            )
                        }
                    }
                }
            })
            if (movieItems.loadState.append == LoadState.Loading)
                PaginationProgress()
            else {
                HandlePagingResult(movieItems)
            }
        }
    }
}