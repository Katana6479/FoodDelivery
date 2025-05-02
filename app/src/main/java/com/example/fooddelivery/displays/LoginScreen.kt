package com.example.fooddelivery.displays

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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddelivery.ui.theme.ButtonBorderGrey
import com.example.fooddelivery.ui.theme.ButtonTextBlack
import com.example.fooddelivery.ui.theme.ButtonWhite
import com.example.fooddelivery.ui.theme.ColorRed
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor
import com.example.fooddelivery.ui.theme.TextRed
import com.example.fooddelivery.viewmodels.LoginViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    onSignInButtonClick: (requestEmail:String, requestPassword:String) -> Unit,
    onSignUpButtonClick: () -> Unit
){
    val viewModel:LoginViewModel = viewModel()
    val userEmail = viewModel.emailState.collectAsState()
    val userPassword = viewModel.passwordState.collectAsState()

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
                            append("Просто ")
                        }
                        withStyle(style = SpanStyle(TextRed)){
                            append("Войдите ")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("и "+" ")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("выберите заведение")
                        }
                    },
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
                Box(modifier = Modifier.weight(1f))
            }
            Spacer(
                modifier = Modifier.height(8.dp)
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
                    text = "Если у вас нет аккаунта, пожалуйта зарегистрируйтесь",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W500,
                    color = Color(0xFF646982)
                )
                Box(modifier = Modifier.weight(1f))
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            InputTextField(
                passwordField = false,
                headerText = "Почта",
                placeholderText = "youremail@gmail.com",
                viewModel = viewModel,
                inputData = userEmail)
            Spacer(
                modifier = Modifier.height(32.dp)
            )
            InputTextField(
                passwordField = true,
                headerText = "Пароль",
                placeholderText = "****",
                viewModel = viewModel,
                inputData = userPassword
            )
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
                onClick = {
                    onSignInButtonClick(
                        userEmail.value,
                        userPassword.value,
                        )
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorRed,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Войти",
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
                    text = "ИЛИ",
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
                        text = "Регистрация",
                        fontSize = 16.sp
                    )
                }
            }

        }
}
@Composable
fun InputTextField(
    passwordField:Boolean,
    headerText:String,
    placeholderText: String,
    viewModel: LoginViewModel,
    inputData: State<String>
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = headerText,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholderText)
            },
            value = inputData.value,
            onValueChange = {newValue->
                if (!passwordField){
                    viewModel.setEmail(newValue)
                }else{
                    viewModel.setPassword(newValue)
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = LoginFieldColor,
                unfocusedBorderColor = LoginFieldBorderColor,
                unfocusedTextColor = LoginFieldTextColor
                //unfocusedContainerColor = Color(0xFFEBEBEB)
            ),
            visualTransformation =
            if (passwordField)
                PasswordVisualTransformation()
            else
                VisualTransformation.None,
            maxLines = 1
        )
        if (passwordField){
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopEnd
            ){
                Text(
                    text = "Забыли пароль?",
                    color = LoginFieldTextColor
                )
            }
        }
    }
}

