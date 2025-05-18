package com.interview.ivanjfbr.moovy.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.interview.ivanjfbr.home.ui.navigation.models.BottomBarRoutes
import com.interview.ivanjfbr.home.ui.navigation.ui.MainNavHost

@Composable
fun MoovyMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navigationItemContentList = listOf(
        BottomBarRoutes.Dashboard,
        BottomBarRoutes.Search,
        BottomBarRoutes.Favorites
    )
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                navigationItemContentList.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = index == 0,
                        onClick = {
                            navController.navigate(route = destination.route)
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
    ) { innerPadding ->
        MainNavHost(navController = navController, innerPadding = innerPadding)
    }
}