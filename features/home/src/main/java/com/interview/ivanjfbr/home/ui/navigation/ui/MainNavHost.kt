package com.interview.ivanjfbr.home.ui.navigation.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.interview.ivanjfbr.home.ui.dashboard.screen.DashboardScreen
import com.interview.ivanjfbr.home.ui.navigation.models.BottomBarRoutes

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
            DashboardScreen()
        }
    }
}