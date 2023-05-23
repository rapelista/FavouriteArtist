@file:OptIn(ExperimentalMaterial3Api::class)

package com.gvstang.dicoding.learning.favouriteartist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gvstang.dicoding.learning.favouriteartist.navigation.Screen
import com.gvstang.dicoding.learning.favouriteartist.ui.component.BottomBar
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.home.HomeScreen
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun FavouriteArtistApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
        }
    }
}

@Preview
@Composable
fun FavouriteArtistAppPreview() {
    FavouriteArtistTheme {
        FavouriteArtistApp()
    }
}