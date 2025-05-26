package com.example.fooddelivery.retrofit

import com.example.fooddelivery.retrofit.data.LoginRequest
import com.example.fooddelivery.retrofit.data.RegisterRequest
import com.example.fooddelivery.retrofit.data.Restaurant
import com.example.fooddelivery.retrofit.data.TokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/register")
    fun register(@Body registerRequest: RegisterRequest): Call<TokenResponse>

    @POST("/api/login")
    fun login(@Body loginRequest: LoginRequest):Call<TokenResponse>

    @GET("/api/restaurants")
    suspend fun getRestaurants(): List<Restaurant>
}