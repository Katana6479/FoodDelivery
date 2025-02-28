package com.example.fooddelivery

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItems(
    val icon: Int,
    val name: String
) {
    data object Home: BottomBarItems(
        icon = R.drawable.home,
        name = "Home"
    )
    data object Search: BottomBarItems(
        icon = R.drawable.search,
        name = "Search"
    )
    data object Favorite: BottomBarItems(
        icon = R.drawable.star,
        name = "Favorite"
    )
    data object Profile: BottomBarItems(
        icon = R.drawable.user,
        name = "Profile"
    )

}