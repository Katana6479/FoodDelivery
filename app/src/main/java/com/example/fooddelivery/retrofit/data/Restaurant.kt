package com.example.fooddelivery.retrofit.data

data class Restaurant (
    val id: Long,
    val name: String,
    val category: String,
    val link: String,
    val rating: Double,
    val description: String
)