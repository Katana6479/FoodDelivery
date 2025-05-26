package com.example.fooddelivery.navigation

import android.net.Uri
import com.example.fooddelivery.retrofit.data.Restaurant
import com.google.gson.Gson

sealed class Screens(
    val route:String
){
    object WelcomeScreen:Screens(DESTINATION_WELCOME)
    object LoginScreen:Screens(DESTINATION_LOGIN)
    object SignUpScreen:Screens(DESTINATION_SIGNUP)
    object HomeScreen:Screens(DESTINATION_HOME)
    object ProfileScreen:Screens(DESTINATION_PROFILE)
    object FavoriteScreen:Screens(DESTINATION_FAVORITE)
    object RestaurantInfo:Screens(DESTINATION_RESTAURANT_INFO){
        private const val ROUTE_FOR_ARGS = "restaurantinfo"
        fun getRouteWithArgs(restaurant: Restaurant):String{
            val restaurantJsonData = Gson().toJson(restaurant)
            return "$ROUTE_FOR_ARGS/${restaurantJsonData.encode()}"
        }
    }
    object MainGraph:Screens(DESTINATION_MAIN_GRAPH)
    companion object{

        const val RESTAURANT_KEY = "restaurant"
        const val DESTINATION_WELCOME = "welcome"
        const val DESTINATION_LOGIN = "login"
        const val DESTINATION_SIGNUP = "signup"
        const val DESTINATION_HOME = "home"
        const val DESTINATION_FAVORITE = "favorite"
        const val DESTINATION_PROFILE = "profile"
        const val DESTINATION_RESTAURANT_INFO = "restaurantinfo/{$RESTAURANT_KEY}"

        const val DESTINATION_MAIN_GRAPH = "main_graph"
    }
}
fun String.encode( ):String{
    return Uri.encode(this)
}
