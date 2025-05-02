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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fooddelivery.ui.theme.ColorRed
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor
import com.example.fooddelivery.ui.theme.TermsClickable
import com.example.fooddelivery.ui.theme.TermsColor
import com.example.fooddelivery.ui.theme.TextRed
import com.example.fooddelivery.viewmodels.SignUpViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen (
    onSignUpClick: ( username:String,userEmail:String, userPassword:String)-> Unit
){
    val signUpViewModel:SignUpViewModel = viewModel()
    val name = signUpViewModel.nameState.collectAsState()
    val email = signUpViewModel.emailState.collectAsState()
    val password = signUpViewModel.passwordState.collectAsState()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(78.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append("Простая ")
                        }
                        withStyle(style = SpanStyle(TextRed)){
                            append("регистрация")
                        }
                        withStyle(style = SpanStyle(MaterialTheme.colorScheme.onBackground)){
                            append(",\n" + "в пару кликов")
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
                    text = "Нет аккаунта? Зарегистрируйтесь!",
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
                        text = "Имя",
                        fontWeight = FontWeight.W600,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = name.value,
                        onValueChange = {newText->
                            signUpViewModel.setName(newText)
                        },
                        placeholder = {
                            Text(text = "Иванов Иван")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LoginFieldColor,
                            unfocusedBorderColor = LoginFieldBorderColor,
                            unfocusedTextColor = LoginFieldTextColor
                        ),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Text)
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
                        text = "Почта",
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email.value,
                        onValueChange = {newText->
                            signUpViewModel.setEmail(newText)
                        },
                        placeholder = {
                            Text(text = "yourmail@gmail.com")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LoginFieldColor,
                            unfocusedBorderColor = LoginFieldBorderColor,
                            unfocusedTextColor = LoginFieldTextColor
                        ),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Email)
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
                    text = "Пароль",
                    fontWeight = FontWeight.W600
                )

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password.value,
                    onValueChange = {newText->
                        signUpViewModel.setPassword(newText)
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LoginFieldColor,
                        unfocusedBorderColor = LoginFieldBorderColor
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions().copy(keyboardType = KeyboardType.Password),
                    placeholder = { Text(text="Придумайте пароль") }
                )
            }
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
                onClick = {
                    onSignUpClick(
                        name.value,
                        email.value,
                        password.value)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ColorRed,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Зарегистрироваться",
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
                    text = "Проходя регистрацию,вы принимаете",
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
                        append("Условия и Положения")
                    }
                    withStyle(style = SpanStyle(TermsColor)){
                        append(" & ")
                    }
                    withStyle(style = SpanStyle(TermsClickable)){
                        append("Политику конфиденциальности")
                    }
                },
                fontSize = 13.sp,
                color = TermsClickable
            )
        }
        }
    }
}