package com.gvstang.dicoding.learning.favouriteartist.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.gvstang.dicoding.learning.favouriteartist.navigation.NavigationItem
import com.gvstang.dicoding.learning.favouriteartist.navigation.Screen
import com.gvstang.dicoding.learning.favouriteartist.ui.theme.FavouriteArtistTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
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
                title ="About",
                icon = Icons.Default.Person,
                screen = Screen.Home
            )
        )

        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.title)
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
                selected = true,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    FavouriteArtistTheme {
        BottomBar()
    }
}