package com.interview.ivanjfbr.home.ui.navigation.models

sealed class Screens(val route: String) {
    data object Dashboard : Screens("dashboard_screen")
    data object Search : Screens("search_screen")
    data object Favorites : Screens("favorites_screen")
    data object SeeAll : Screens("see_all_screen")
    data object MovieDetailScreen : Screens("movie_detail_screen")
}