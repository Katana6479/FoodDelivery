package com.example.fooddelivery.displays

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import com.example.fooddelivery.ui.theme.ColorRed
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor
import com.example.fooddelivery.ui.theme.TermsClickable
import com.example.fooddelivery.ui.theme.TermsColor
import com.example.fooddelivery.ui.theme.TextRed

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen (
    onSignUpClick: ()-> Unit
){
    Scaffold {
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
                            append("Let’s ")
                        }
                        withStyle(style = SpanStyle(TextRed)){
                            append("Sign you up")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append(",\n" + "your meal awaits")
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
                        text = "Full name",
                        fontWeight = FontWeight.W600,
                    )
                    val text = "Sam Smith"
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = text,
                        onValueChange = {},
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LoginFieldColor,
                            unfocusedBorderColor = LoginFieldBorderColor,
                            unfocusedTextColor = LoginFieldTextColor
                        )
                    )
                }
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
                        fontWeight = FontWeight.W600
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
                        )
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Password",
                    fontWeight = FontWeight.W600
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
            }
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
                onClick = {
                    onSignUpClick()
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorRed,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "SIGN UP",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
            Box(
                modifier = Modifier.wrapContentHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "By signing up, you have agreed to our",
                    color = TermsColor
                )
            }
            Box(
                    modifier = Modifier.wrapContentHeight()
                        .fillMaxWidth(),
            contentAlignment = Alignment.Center
            ){
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(TermsClickable)){
                        append("Terms and conditions")
                    }
                    withStyle(style = SpanStyle(TermsColor)){
                        append(" & ")
                    }
                    withStyle(style = SpanStyle(TermsClickable)){
                        append("Privacy policy")
                    }
                },
                color = TermsClickable
            )
        }


        }
    }
}