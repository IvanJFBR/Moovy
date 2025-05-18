package com.interview.ivanjfbr.home.ui.navigation.models

sealed class Screens(val route: String) {
    object Dashboard : Screens("dashboard_screen")
    object Search : Screens("search_screen")
    object Favorites : Screens("favorites_screen")
    object ViewAll : Screens("view_all_screen")
    object MovieDetailsScreen : Screens("movie_details_screen")
}