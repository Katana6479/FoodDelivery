package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fooddelivery.retrofit.data.Restaurant

class NavigationState(
    val navHostController: NavHostController
){
    fun navigateTo(
        screen: String
    ){
        navHostController.navigate(screen){
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState
            }
            launchSingleTop= true
            restoreState = true
        }
    }
    fun navigateToInfo(restaurant: Restaurant){
        navHostController.navigate(Screens.RestaurantInfo.getRouteWithArgs(restaurant))
    }

}
@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
):NavigationState{
    return remember {
        NavigationState(navHostController)
    }
}