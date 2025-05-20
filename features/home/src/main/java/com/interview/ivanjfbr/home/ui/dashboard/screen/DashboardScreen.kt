package com.interview.ivanjfbr.home.ui.dashboard.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.interview.ivanjfbr.core.ui.MoovyDefaultTheme
import com.interview.ivanjfbr.core.ui.UiState
import com.interview.ivanjfbr.core.ui.components.ErrorView
import com.interview.ivanjfbr.core.ui.components.LoadingView
import com.interview.ivanjfbr.core.ui.components.MovieCard
import com.interview.ivanjfbr.home.R
import com.interview.ivanjfbr.home.data.model.MoviesSectionResponse
import com.interview.ivanjfbr.home.data.network.MoviesCategoryUrl
import com.interview.ivanjfbr.home.ui.dashboard.DashboardViewModel
import com.interview.ivanjfbr.home.ui.navigation.models.BottomBarRoutes
import com.interview.ivanjfbr.home.ui.navigation.models.Screens

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    MoovyDefaultTheme(
        bottomBar = {
            val navigationItemContentList = listOf(
                BottomBarRoutes.Dashboard,
                BottomBarRoutes.Search,
                BottomBarRoutes.Favorites
            )
            var selectedDestination by rememberSaveable { mutableIntStateOf(0) }

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            if (navigationItemContentList.find { it.route == currentDestination?.route } != null) {
                NavigationBar {
                    navigationItemContentList.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = index == 0,
                            onClick = {
                                navController.navigate(route = BottomBarRoutes.Dashboard.route)
                                selectedDestination = index
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = stringResource(destination.titleResId)
                                )
                            }
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn {
            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.now_playing),
                    uiState = viewModel.nowPlayingState.collectAsStateWithLifecycle().value,
                    category = MoviesCategoryUrl.NOW_PLAYING,
                    navController = navController
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.popular),
                    uiState = viewModel.popularState.collectAsStateWithLifecycle().value,
                    category = MoviesCategoryUrl.POPULAR,
                    navController = navController
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.top_rated),
                    uiState = viewModel.topRatedState.collectAsStateWithLifecycle().value,
                    category = MoviesCategoryUrl.TOP_RATED,
                    navController = navController
                )
            }

            item {
                RenderMoviesSectionList(
                    sectionTitle = stringResource(R.string.upcoming),
                    uiState = viewModel.upcomingState.collectAsStateWithLifecycle().value,
                    category = MoviesCategoryUrl.UPCOMING,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RenderMoviesSectionList(
    sectionTitle: String,
    uiState: UiState<MoviesSectionResponse>,
    category: String,
    navController: NavController
) {
    when (uiState) {
        is UiState.Loading -> {
            LoadingView()
        }
        is UiState.Success -> {
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = sectionTitle,
                        style = MaterialTheme.typography.headlineSmall,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.clickable(
                            onClick = {
                                navController.navigate(Screens.SeeAll.route + "?categoryUrl=${category}&sectionTitle=${sectionTitle}")
                            },
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.seeAll),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(end = 4.dp),
                        )
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = stringResource(R.string.seeAll)
                        )
                    }
                }

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
        }

        is UiState.Error -> {
            ErrorView()
        }
    }
}