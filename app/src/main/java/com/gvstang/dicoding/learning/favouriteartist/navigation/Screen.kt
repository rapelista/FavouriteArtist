package com.gvstang.dicoding.learning.favouriteartist.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object About: Screen("about")
}
