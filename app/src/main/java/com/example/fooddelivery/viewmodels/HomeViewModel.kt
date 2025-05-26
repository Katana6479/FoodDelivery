package com.example.fooddelivery.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddelivery.retrofit.ApiClient
import com.example.fooddelivery.retrofit.data.Restaurant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    // Состояние текстового поля поиска
    private val _textFieldState = MutableStateFlow("")
    val textFieldState: StateFlow<String> = _textFieldState

    // История поиска
    private val searchHistoryInitialList = listOf(
        "Москва, Белый Кролик",
        "Люберцы, Нани",
        "Москва, The Бык",
        "Москва, Frank By Basta"
    )
    private val _searchHistoryList = MutableStateFlow(searchHistoryInitialList)
    val searchHistoryList: StateFlow<List<String>> = _searchHistoryList

    // Видимость кнопки очистки
    private val _clearButtonState = MutableStateFlow(false)
    val clearButtonState: StateFlow<Boolean> = _clearButtonState

    // DataStore для истории поиска
    private val searchHistoryManager = SearchHistoryController(application)
    private val _searchHistory = MutableStateFlow<Set<String>>(emptySet())
    val searchHistory: StateFlow<Set<String>> = _searchHistory.asStateFlow()

    // Данные ресторанов
    private val _allRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    private val _filteredRestaurants = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurants: StateFlow<List<Restaurant>> = _filteredRestaurants.asStateFlow()

    // Состояние загрузки
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // Ошибки
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            // Загрузка истории поиска
            searchHistoryManager.searchHistoryFlow.collect { history ->
                _searchHistory.update { history }
            }
        }
        loadRestaurants()
    }

    // Обновление текста поиска
    fun updateTextFieldState(newStateValue: String) {
        _textFieldState.value = newStateValue
        // Автоматическая фильтрация при изменении текста
        filterRestaurants(newStateValue)
    }

    // Обновление состояния кнопки очистки
    fun updateClearButtonState(newStateValue: Boolean) {
        _clearButtonState.value = newStateValue
    }

    // Добавление запроса в историю
    fun addSearchQuery(query: String) {
        viewModelScope.launch {
            searchHistoryManager.addSearchQuery(query)
            // Применяем фильтр после добавления запроса
            filterRestaurants(query)
        }
    }

    // Очистка истории поиска
    fun clearSearchHistory() {
        viewModelScope.launch {
            searchHistoryManager.clearSearchHistory()
        }
    }

    // Загрузка ресторанов с API
    fun loadRestaurants() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val restaurants = ApiClient.authApi.getRestaurants()
                _allRestaurants.value = restaurants
                _filteredRestaurants.value = restaurants // Показываем все рестораны по умолчанию
            } catch (e: Exception) {
                _error.value = "Ошибка загрузки ресторанов: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    // Фильтрация по категории
    fun filterByCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }

    // Сброс фильтра по категории
    fun resetCategoryFilter() {
        _selectedCategory.value = null
        applyFilters()
    }

    // Объединенная фильтрация (по категории и текстовому поиску)
    private fun applyFilters() {
        var filtered = _allRestaurants.value

        // Фильтр по категории
        _selectedCategory.value?.let { category ->
            filtered = filtered.filter { it.category.contains(category, ignoreCase = true) }
        }

        // Фильтр по текстовому поиску
        if (_textFieldState.value.isNotBlank()) {
            filtered = filtered.filter { restaurant ->
                restaurant.name.contains(_textFieldState.value, ignoreCase = true) ||
                        restaurant.category.contains(_textFieldState.value, ignoreCase = true)
            }
        }

        _filteredRestaurants.value = filtered
    }

    // Обновите существующие методы для вызова applyFilters()
    private fun filterRestaurants(query: String) {
        _textFieldState.value = query
        applyFilters()
    }

    fun resetSearch() {
        _textFieldState.value = ""
        applyFilters()
    }
}