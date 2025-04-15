package com.example.fooddelivery.viewmodels

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "search_history")

class SearchHistoryController(private val context: Context) {

    private val searchHistoryKey = stringSetPreferencesKey("search_history")
    val searchHistoryFlow: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[searchHistoryKey] ?: emptySet()
    }

    suspend fun addSearchQuery(query: String) {
        context.dataStore.edit { preferences ->
            val currentHistory = preferences[searchHistoryKey]?.toMutableSet() ?: mutableSetOf()
            currentHistory.add(query)
            preferences[searchHistoryKey] = currentHistory
        }
    }

    suspend fun clearSearchHistory() {
        context.dataStore.edit { preferences ->
            preferences[searchHistoryKey] = emptySet()
        }
    }
}
