package com.gvstang.dicoding.learning.favouriteartist.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
    object Favourite: Screen("favourite")
    object DetailArtist : Screen("home/{name}") {
        fun createRoute(name: String) = "home/$name"
    }
}
