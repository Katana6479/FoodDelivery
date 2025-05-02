package com.example.fooddelivery.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignUpViewModel: ViewModel() {
    private val _nameState = MutableStateFlow("")
    val nameState: StateFlow<String> = _nameState
    fun setName(newName:String){
        _nameState.value = newName
    }
    private val _emailState = MutableStateFlow("")
    val emailState: StateFlow<String> = _emailState
    fun setEmail(newName:String){
        _emailState.value = newName
    }
    private val _passwordState = MutableStateFlow("")
    val passwordState: StateFlow<String> = _passwordState
    fun setPassword(newName:String){
        _passwordState.value = newName
    }
}