package com.example.fooddelivery.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.ColorYellow

@Composable
fun WelcomeScreen(
    onNextButtonClick: () -> Unit
){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                modifier = Modifier
                    .size(
                        width = 339.dp,
                        height = 480.dp
                    ),
                painter = painterResource(R.drawable.welcome_logo),
                contentDescription = null
            )
            Button(
                modifier = Modifier
                    .width(306.dp)
                    .height(66.dp),
                onClick = {
                    onNextButtonClick()
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorYellow,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Start",
                    fontSize = 16.sp
                )
            }
        }
}