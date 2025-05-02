package com.example.fooddelivery.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.retrofit.ApiClient
import com.example.fooddelivery.retrofit.data.LoginRequest
import com.example.fooddelivery.retrofit.data.RegisterRequest
import com.example.fooddelivery.retrofit.data.TokenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenVM:ViewModel() {
    private val _registrationState = MutableStateFlow(false)
    private val _registrationStateMessage = MutableStateFlow("")
    val registrationState: StateFlow<Boolean> = _registrationState
    private val _loginState = MutableStateFlow(false)
    private val _loginStateMessage = MutableStateFlow("")
    val loginState: StateFlow<Boolean> = _loginState
    fun registerUser(registerRequest: RegisterRequest) {
        Log.d("registration","vm registration start")
        try {
            viewModelScope.launch {
                ApiClient.authApi.register(registerRequest)
                    .enqueue(object : Callback<TokenResponse> {
                        override fun onResponse(
                            call: Call<TokenResponse>,
                            response: Response<TokenResponse>
                        ) {
                            if (response.isSuccessful) {
                                val token = response.body()?.token
                                _registrationState.value = true
                                Log.d("registration", "Token received: $token")
                            } else {
                                _registrationStateMessage.value =
                                    "Регистрация не удалась: ${response.message()}"
                            }
                        }

                        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                            Log.d("registration","Registration failed: ${t.message}")
                            _registrationStateMessage.value = "Registration failed: ${t.message}"
                        }
                    })
            }
        }catch (e:Exception){
            Log.d("registration","exepc")
            _registrationStateMessage.value = "Unexpected error: ${e.message}"
        }
    }
    fun loginUser(loginRequest: LoginRequest){
        try{
            viewModelScope.launch {
                ApiClient.authApi.login(loginRequest)
                    .enqueue(object: Callback<TokenResponse>{
                        override fun onResponse(
                            call: Call<TokenResponse>,
                            response: Response<TokenResponse>
                        ) {
                            if(response.isSuccessful){
                                val token = response.body()?.token
                                _loginState.value = true
                                Log.d("login", "Login Token received: $token")
                            }else {
                                _loginStateMessage.value =
                                    "Войти не удалось: ${response.message()}"
                            }
                        }

                        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                            Log.d("login","Login failed: ${t.message}")
                            _loginStateMessage.value = "Login failed: ${t.message}"
                        }
                    })
            }
        }catch (e:Exception){
            Log.d("login","exepc")
            _loginStateMessage.value = "Unexpected error: ${e.message}"
        }

    }
    private val _bottomBarVisibilityState = MutableStateFlow(false)
    private val _isDarkThemeState = MutableStateFlow(false)
    val bottomBarVisibilityState:StateFlow<Boolean> = _bottomBarVisibilityState
    val isDarkThemeState:StateFlow<Boolean> = _isDarkThemeState

    fun setBottomBarVisibility(newVisibility:Boolean){
        _bottomBarVisibilityState.value = newVisibility
    }
    fun setDarkThemeState(newState:Boolean){
        _isDarkThemeState.value = newState
    }
}