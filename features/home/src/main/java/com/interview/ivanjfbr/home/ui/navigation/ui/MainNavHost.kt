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
            route = Screens.MovieDetailScreen.route + "/{movieId}",
            arguments = listOf(navArgument(name = "movieId") { type = NavType.IntType })
        ) {
            MovieDetailScreen()
        }
    }
}