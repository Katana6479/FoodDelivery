package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    loginScreenContent: @Composable ()->Unit,
    signUpScreenContent: @Composable ()->Unit,
    welcomeScreenContent: @Composable ()->Unit,
    homeScreenContent: @Composable ()->Unit,
    profileScreenContent: @Composable ()->Unit,
    favoritesScreenContent: @Composable () ->Unit
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
            homeScreenContent = homeScreenContent,
            profileScreenContent = profileScreenContent,
            favoritesScreenContent = favoritesScreenContent
        )
    }
}