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
        composable(Screens.DESTINATION_WELCOME){
            welcomeScreenContent()
        }
        composable(Screens.DESTINATION_LOGIN){
            loginScreenContent()
        }
        composable(Screens.DESTINATION_SIGNUP){
            signUpScreenContent()
        }
        composable(Screens.DESTINATION_HOME){
            homeScreenContent()
        }
        composable(Screens.DESTINATION_PROFILE){
            profileScreenContent()
        }
        composable(Screens.DESTINATION_FAVORITE){
            favoritesScreenContent()
        }
    }
}