@file:OptIn(ExperimentalMaterial3Api::class)

package com.gvstang.dicoding.learning.favouriteartist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gvstang.dicoding.learning.favouriteartist.navigation.NavigationItem
import com.gvstang.dicoding.learning.favouriteartist.navigation.Screen
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.about.AboutScreen
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.detail.DetailScreen
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.favourite.FavouriteScreen
import com.gvstang.dicoding.learning.favouriteartist.ui.screen.home.HomeScreen
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun FavouriteArtistApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if(currentRoute != Screen.DetailArtist.route) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { name ->
                        navController.navigate(Screen.DetailArtist.createRoute(name))
                    }
                )
            }
            composable(Screen.Favourite.route) {
                FavouriteScreen(
                    navigateToDetail = { name ->
                        navController.navigate(Screen.DetailArtist.createRoute(name))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailArtist.route,
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    }
                )
            ) {
                val name = it.arguments?.getString("name") ?: ""
                DetailScreen(
                    name = name,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title ="Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title ="Favourite",
                icon = Icons.Default.Favorite,
                screen = Screen.Favourite
            ),
            NavigationItem(
                title ="About",
                icon = Icons.Default.Person,
                screen = Screen.About
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = if(item.screen == Screen.About) "about_page" else item.title)
                },
                label = {
                    Text(
                        text = item.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.tertiary,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
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