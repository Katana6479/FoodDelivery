package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.homeScreenNavGraph(
    homeScreenContent: @Composable ()->Unit,
){
    navigation(
        startDestination = Screens.MainGraph.route,
        route = "123"
    ){
        composable(Screens.MainGraph.route
        ){
            homeScreenContent()
        }
    }
}