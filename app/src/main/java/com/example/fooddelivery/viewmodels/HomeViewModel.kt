package com.example.fooddelivery.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel:ViewModel() {
    private val _textFieldState = MutableStateFlow("")
    val textFieldState: StateFlow<String> = _textFieldState
    fun updateTextFieldState(newStateValue:String){
        _textFieldState.value = newStateValue
    }
    private val searchHistory = listOf("Москва, Белый Кролик", "Люберцы, Нани", "Москва, The Бык", "Москва, Frank By Basta")

    private val _searchHistoryList = MutableStateFlow(searchHistory)
    val searchHistoryList: StateFlow<List<String>> = _searchHistoryList
    fun updateHistoryList(){

    }

    private val _clearButtonState = MutableStateFlow(false)
    val clearButtonState:StateFlow<Boolean> = _clearButtonState
    fun updateClearButtonState(newStateValue:Boolean){
        _clearButtonState.value = newStateValue
    }
}