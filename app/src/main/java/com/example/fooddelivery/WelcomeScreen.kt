package com.example.fooddelivery

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun WelcomeScreen(){
    Scaffold (
        modifier = Modifier.fillMaxSize()
    ){paddingValues ->
        Image(
            modifier = Modifier
            .fillMaxSize(),
            painter = painterResource(R.drawable.logo_food_restaurants),
            contentDescription = null
        )
        Text(text = "123", modifier = Modifier.padding(paddingValues))
    }
}