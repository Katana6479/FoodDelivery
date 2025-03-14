package com.example.fooddelivery

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.fooddelivery.navigation.Screens

sealed class BottomBarItems(
    val icon: Int,
    val name: String,
    val screen: Screens
) {
    data object Home: BottomBarItems(
        icon = R.drawable.baseline_home_filled_24,
        name = "Home",
        screen = Screens.HomeScreen
    )
    data object Favorite: BottomBarItems(
        icon = R.drawable.star,
        name = "Favorite",
        screen = Screens.FavoriteScreen
    )
    data object Profile: BottomBarItems(
        icon = R.drawable.user,
        name = "Profile",
        screen = Screens.ProfileScreen
    )

}