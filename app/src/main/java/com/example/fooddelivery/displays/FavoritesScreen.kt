package com.example.fooddelivery.displays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoritesScreen(
    paddingValues: PaddingValues
){
    Box(
        modifier = Modifier.padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    )
}