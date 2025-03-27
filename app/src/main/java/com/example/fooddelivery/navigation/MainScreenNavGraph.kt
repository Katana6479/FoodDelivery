package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.mainScreenNavGraph(
    homeScreenContent: @Composable () ->Unit,
    profileScreenContent: @Composable ()->Unit,
    favoritesScreenContent: @Composable () ->Unit
){
    navigation(
        startDestination =Screens.HomeScreen.route,
        route = Screens.MainGraph.route
    ){
        composable(Screens.HomeScreen.route){
            homeScreenContent()
        }
        composable(Screens.ProfileScreen.route){
            profileScreenContent()
        }
        composable(Screens.FavoriteScreen.route){
            favoritesScreenContent()
        }
    }
}