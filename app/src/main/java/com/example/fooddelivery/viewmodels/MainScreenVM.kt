package com.example.fooddelivery.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.retrofit.ApiClient
import com.example.fooddelivery.retrofit.data.RegisterRequest
import com.example.fooddelivery.retrofit.data.TokenResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainScreenVM:ViewModel() {
    private val _registrationState = MutableStateFlow("")
    val registrationState = _registrationState
    fun registerUser(registerRequest: RegisterRequest) {
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
                                _registrationState.value = "Successful"
                                Log.d("Auth", "Token received: $token")
                            } else {
                                _registrationState.value =
                                    "Регистрация не удалась: ${response.message()}"
                            }
                        }

                        override fun onFailure(call: Call<TokenResponse>, t: Throwable) {
                            _registrationState.value = "Registration failed: ${t.message}"
                        }
                    })
            }
        }catch (e:Exception){
            _registrationState.value = "Unexpected error: ${e.message}"
        }
    }

}