package com.interview.ivanjfbr.home.ui.navigation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interview.ivanjfbr.home.ui.dashboard.screen.DashboardScreen
import com.interview.ivanjfbr.home.ui.movie_detail.screen.MovieDetailScreen
import com.interview.ivanjfbr.home.ui.navigation.models.BottomBarRoutes
import com.interview.ivanjfbr.home.ui.navigation.models.Screens
import com.interview.ivanjfbr.home.ui.see_all.screen.SeeAllScreen

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarRoutes.Dashboard.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomBarRoutes.Dashboard.route) {
            DashboardScreen(navController)
        }

        composable(
            route = Screens.MovieDetailScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(navArgument(name = "movieId") { type = NavType.IntType })
        ) {
            val moviesTitle = it.arguments?.getString("moviesTitle") ?: ""
            MovieDetailScreen(moviesTitle)
        }

        composable(
            route = Screens.SeeAll.route + "?categoryUrl={categoryUrl}", arguments = listOf(
                navArgument(name = "categoryUrl") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            SeeAllScreen(navController = navController)
        }
    }
}