package com.interview.ivanjfbr.moovy.ui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.interview.ivanjfbr.home.ui.navigation.ui.MainNavHost

@Composable
fun MoovyMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Surface (
        modifier = modifier
    ) {
        MainNavHost(navController = navController)
    }
}