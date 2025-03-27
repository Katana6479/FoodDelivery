package com.example.fooddelivery.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.ui.theme.ButtonBorderGrey
import com.example.fooddelivery.ui.theme.ButtonTextBlack
import com.example.fooddelivery.ui.theme.ButtonWhite
import com.example.fooddelivery.ui.theme.ColorRed
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor
import com.example.fooddelivery.ui.theme.TextRed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    onSignInButtonClick: () -> Unit,
    onSignUpButtonClick: () -> Unit
){
    val color = MaterialTheme.colorScheme.onBackground
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("Just ")
                        }
                        withStyle(style = SpanStyle(TextRed)){
                            append("Sign in ")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("we’ll"+"\n")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("prepare your order")
                        }
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Box(modifier = Modifier.weight(1f))
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    text = " If you don’t have an account please sign up",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF646982)
                )
                Box(modifier = Modifier.weight(1f))
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Email",
                        fontWeight = FontWeight.Bold
                    )
                    val text = "foodhub@gmail.com"
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = text,
                        onValueChange = {},
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LoginFieldColor,
                            unfocusedBorderColor = LoginFieldBorderColor,
                            unfocusedTextColor = LoginFieldTextColor
                            //unfocusedContainerColor = Color(0xFFEBEBEB)
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(32.dp)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Password",
                    fontWeight = FontWeight.Bold
                )
                val text = "****"

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = {},
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LoginFieldColor,
                        unfocusedBorderColor = LoginFieldBorderColor
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd
                ){
                    Text(
                        text = "Forgot password?",
                        color = LoginFieldTextColor
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
                onClick = {
                    onSignInButtonClick()
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorRed,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "SIGN IN",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "OR",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(53.dp),
                    onClick = {
                        onSignUpButtonClick()
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonWhite,
                        contentColor = ButtonTextBlack
                    ),
                    border = BorderStroke(2.dp, ButtonBorderGrey)
                ) {
                    Text(
                        text = "SIGN UP",
                        fontSize = 16.sp
                    )
                }
            }

        }
}

@Composable
fun TopText(
    paddingValues: PaddingValues,
    ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(paddingValues)
    ) {
        Text(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f),
            text = " Just Sign in,we’ll prepar your order",
            fontSize = 24.sp
        )
        Box(modifier = Modifier.weight(1f))
    }
}