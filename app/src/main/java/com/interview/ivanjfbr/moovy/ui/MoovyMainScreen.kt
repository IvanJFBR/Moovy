package com.interview.ivanjfbr.moovy.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
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
import androidx.navigation.compose.currentBackStackEntryAsState
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

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (navigationItemContentList.find { it.route == currentDestination?.route } != null) {
                NavigationBar() {
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
    ) { innerPadding ->
        MainNavHost(navController = navController, innerPadding = innerPadding)
    }
}