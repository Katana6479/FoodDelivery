package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fooddelivery.retrofit.data.Restaurant

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    loginScreenContent: @Composable ()->Unit,
    signUpScreenContent: @Composable ()->Unit,
    welcomeScreenContent: @Composable ()->Unit,
    homeScreenContent: @Composable ()->Unit,
    profileScreenContent: @Composable ()->Unit,
    favoritesScreenContent: @Composable () ->Unit,
    restaurantInfoContent: @Composable (Restaurant) ->Unit
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.WelcomeScreen.route
    ) {
        composable(Screens.WelcomeScreen.route){
            welcomeScreenContent()
        }
        loginScreenNavGraph(
            loginScreenContent = loginScreenContent,
            signUpScreenContent = signUpScreenContent
        )
        mainScreenNavGraph (
            profileScreenContent = profileScreenContent,
            favoritesScreenContent = favoritesScreenContent
        )
        homeScreenNavGraph(homeScreenContent = homeScreenContent,
            restaurantInfoContent = restaurantInfoContent)
    }
}