package com.example.fooddelivery.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YandexApi {
    @GET("?")
    suspend fun getRestaurantByName(
        @Query("text") restaurantName: String,
        @Query("type") type: String = "biz",
        @Query("lang") lang: String = "ru_RU",
        @Query("results") results: Int = 1,
        @Query("apikey") apiKey: String = "74c5efd2-1834-421e-8bcf-fb1357050190"
    ): Restaurant
}