package com.interview.ivanjfbr.home.ui.navigation.models

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.interview.ivanjfbr.home.R

sealed class BottomBarRoutes {
    object Dashboard : BottomBar(
        route = Screens.Dashboard.route,
        titleResId = R.string.dashboard,
        icon = Icons.Outlined.Dashboard,
        iconFocused = Icons.Filled.Dashboard
    )

    object Search : BottomBar(
        route = Screens.Search.route,
        titleResId = R.string.search,
        icon = Icons.Outlined.Search,
        iconFocused = Icons.Filled.Search
    )

    object Favorites : BottomBar(
        route = Screens.Favorites.route,
        titleResId = R.string.favorites,
        icon = Icons.Outlined.Favorite,
        iconFocused = Icons.Filled.Favorite
    )
}

sealed class BottomBar(
    val route: String,
    @StringRes val titleResId: Int,
    val icon: ImageVector,
    val iconFocused: ImageVector
)