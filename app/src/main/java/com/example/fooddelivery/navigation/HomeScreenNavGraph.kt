package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.fooddelivery.retrofit.data.Restaurant
import com.google.gson.Gson

fun NavGraphBuilder.homeScreenNavGraph(
    homeScreenContent: @Composable ()->Unit,
    restaurantInfoContent: @Composable (Restaurant)->Unit
){
    navigation(
        startDestination = Screens.HomeScreen.route,
        route = "home_screen_graph"
    ){
        composable(Screens.HomeScreen.route
        ){
            homeScreenContent()
        }
        composable(route = Screens.RestaurantInfo.route,
            arguments = listOf(
                navArgument(Screens.RESTAURANT_KEY) {
                    type = NavType.StringType
                }
            )
        ){
            val restaurantJson = it.arguments?.getString(Screens.RESTAURANT_KEY) ?: ""
            val restaurant = Gson().fromJson<Restaurant>(restaurantJson, Restaurant::class.java)
            restaurantInfoContent(restaurant)
        }
    }
}