package com.example.fooddelivery

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HomeViewModel:ViewModel() {
    private val _textFieldState = mutableStateOf("")
    val textFieldState = _textFieldState
    fun updateState(newStateValue:String){
        _textFieldState.value = newStateValue
    }
}