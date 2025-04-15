package com.example.fooddelivery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(application: Application):AndroidViewModel(application) {
    private val _textFieldState = MutableStateFlow("")
    val textFieldState: StateFlow<String> = _textFieldState
    fun updateTextFieldState(newStateValue:String){
        _textFieldState.value = newStateValue
    }
    private val searchHistoryInitialList = listOf("Москва, Белый Кролик", "Люберцы, Нани", "Москва, The Бык", "Москва, Frank By Basta")

    private val _searchHistoryList = MutableStateFlow(searchHistoryInitialList)
    val searchHistoryList: StateFlow<List<String>> = _searchHistoryList
    fun updateHistoryList(){

    }

    private val _clearButtonState = MutableStateFlow(false)
    val clearButtonState:StateFlow<Boolean> = _clearButtonState
    fun updateClearButtonState(newStateValue:Boolean){
        _clearButtonState.value = newStateValue
    }

    //Data store vm
    private val searchHistoryManager = SearchHistoryController(application)
    private val _searchHistory = MutableStateFlow<Set<String>>(emptySet())
    val searchHistory: StateFlow<Set<String>> = _searchHistory.asStateFlow()

    init {
        viewModelScope.launch {
            searchHistoryManager.searchHistoryFlow.collect { history ->
                _searchHistory.update { history }
            }
        }
    }

    fun addSearchQuery(query: String) {
        viewModelScope.launch {
            searchHistoryManager.addSearchQuery(query)
        }
    }

    fun clearSearchHistory() {
        viewModelScope.launch {
            searchHistoryManager.clearSearchHistory()
        }
    }
}