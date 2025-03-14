package com.example.fooddelivery.navigation

sealed class Screens(
    val route:String
){
    object WelcomeScreen:Screens(DESTINATION_WELCOME)
    object LoginScreen:Screens(DESTINATION_LOGIN)
    object SignUpScreen:Screens(DESTINATION_SIGNUP)
    object HomeScreen:Screens(DESTINATION_HOME)
    object ProfileScreen:Screens(DESTINATION_PROFILE)
    object FavoriteScreen:Screens(DESTINATION_FAVORITE)
    companion object{
        const val DESTINATION_WELCOME = "welcome"
        const val DESTINATION_LOGIN = "login"
        const val DESTINATION_SIGNUP = "signup"
        const val DESTINATION_HOME = "home"
        const val DESTINATION_FAVORITE = "favorite"
        const val DESTINATION_PROFILE = "profile"
    }
}
