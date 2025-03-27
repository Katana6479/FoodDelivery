package com.example.fooddelivery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.loginScreenNavGraph(
    loginScreenContent: @Composable ()->Unit,
    signUpScreenContent: @Composable ()->Unit
){
    navigation(
        startDestination = Screens.LoginScreen.route,
        route="login_nav_graph"
    ){
        composable(Screens.LoginScreen.route){
            loginScreenContent()
        }
        composable(Screens.SignUpScreen.route){
            signUpScreenContent()
        }
    }

}