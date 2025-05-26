package com.example.fooddelivery.displays

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.fooddelivery.viewmodels.HomeViewModel
import com.example.fooddelivery.R
import com.example.fooddelivery.retrofit.data.Restaurant
import com.example.fooddelivery.ui.theme.ColorYellow
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    clickedRestaurant: (Restaurant) ->Unit
){
    val viewModel: HomeViewModel = viewModel()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val listState = rememberLazyListState()
    val searchHistory by viewModel.searchHistory.collectAsState()
    val textFieldState = viewModel.textFieldState.collectAsState()
    val clearButtonVisibilityState = viewModel.clearButtonState.collectAsState()
    val restaurants by viewModel.restaurants.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    error?.let {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
        return
    }

    LazyColumn (
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            SearchField(
                viewModel,
                searchHistory,
                textFieldState = textFieldState.value,
                clearButtonVisibilityState = clearButtonVisibilityState.value
            )
        }
        item{
            selectedCategory?.let { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Категория: $category",
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(
                        onClick = { viewModel.resetCategoryFilter() }
                    ) {
                        Text("Сбросить")
                    }
                }
            }
        }
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                DisplayImage(
                    link = "https://eda.yandex/images/1380157/fd0b8c91c2c2a137020053cb3c35c37c-1100x825.jpg",
                    categoryName = "Высокая кухня"
                )
                DisplayImage(
                    link = "https://eda.yandex/images/3377781/0bd6643e4c33621295ef9862e55f95d7-1100x825.jpg",
                    categoryName = "Роллы"
                )
                DisplayImage(
                    link = "https://cdn.kobo.com/book-images/cccc79b9-58cc-488a-a2a0-6db53b84114f/1200/1200/False/the-healthy-90.jpg",
                    categoryName = "Стейки"
                )
                DisplayImage(
                    link = "https://images.deliveryhero.io/image/fd-pk/LH/lbex-hero.jpg",
                    categoryName = "Пицца"
                )
            }
        }

        items(restaurants.size, {it}){restaurantId->
            val restaurant = restaurants[restaurantId]
            RestaurantCard(
                restaurant = restaurant,
                onRestaurantClick =  clickedRestaurant
            )
        }
    }

}

@Composable
fun SearchField(
    viewModel: HomeViewModel,
    searchHistory: Set<String>,
    textFieldState: String,
    clearButtonVisibilityState: Boolean

    ){

    val keyboardController = LocalSoftwareKeyboardController.current
    val showSuggestions = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
        .fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = textFieldState,
            placeholder = {
                Text(text = "Найдите ресторан")
            },
            singleLine = true,
            onValueChange = { newValue ->
                viewModel.updateTextFieldState(newValue)
                viewModel.updateClearButtonState(true)
                showSuggestions.value = newValue.isNotEmpty()
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedBorderColor = LoginFieldBorderColor,
                unfocusedTextColor = LoginFieldTextColor
            ),
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (textFieldState != "" && clearButtonVisibilityState) {
                        Text(
                            text = "Сброс",
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .clickable(
                                    onClick = {
                                        viewModel.updateTextFieldState("")
                                        viewModel.resetSearch()
                                        keyboardController?.hide()
                                        showSuggestions.value = false
                                    }
                                )
                        )
                    } else {
                        Text(text = "")
                    }
                    IconButton(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(30)),
                        colors = IconButtonDefaults.iconButtonColors().copy(
                            containerColor = ColorYellow
                        ),
                        onClick = {
                            viewModel.addSearchQuery(textFieldState)
                            keyboardController?.hide()
                            showSuggestions.value = false
                        }
                    ) {
                        Icon(painterResource(R.drawable.search), contentDescription = null)
                    }
                }
            },
            shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
        )
    }
    if (searchHistory.isNotEmpty()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            DropdownMenu(
                expanded = showSuggestions.value,
                offset = DpOffset(x= 26.dp, y = (-18).dp),
                modifier = Modifier.padding(end = 16.dp),
                onDismissRequest = { showSuggestions.value = false },
                properties = PopupProperties(focusable = false)
            ) {
                searchHistory.reversed().forEach { searchHistoryItem ->
                    DropdownMenuItem(
                        modifier = Modifier.width(320.dp),
                        text = {
                            Text(
                                text = searchHistoryItem,
                                fontWeight = FontWeight.W600)
                               },
                        onClick = {
                            viewModel.updateTextFieldState(searchHistoryItem)
                            keyboardController?.hide()
                            showSuggestions.value = false
                        }
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            viewModel.clearSearchHistory()
                        },
                    text = "Очистить историю",
                    fontSize = 12.sp
                )
            }
        }
    }
}
@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    onRestaurantClick: (Restaurant) -> Unit
){
    val cardImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(restaurant.link)
            .transformations(RoundedCornersTransformation(12F))
            .build()
    )
    Spacer(Modifier.height(16.dp))
    Card (
        modifier = Modifier
            .clickable (
                enabled = true,
                onClick = {onRestaurantClick(restaurant)}
            )
    ){
        Column(
        ){
            Image(
                painter = cardImagePainter,
                modifier =Modifier
                    .size(328.dp, 211.dp),
                contentDescription = null
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 2.dp),
                text = restaurant.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 2.dp),
                text = restaurant.category,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
            Spacer(
                modifier = Modifier.height(12.dp)
            )
            Row (
                modifier = Modifier
                    .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = restaurant.rating.toString()
                )
                Icon(painter = painterResource(R.drawable.baseline_star_rate_24),
                    contentDescription = null)
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = "45 мин"
                )
                Icon(painter = painterResource(R.drawable.service_time),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null)
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = "Отзывы"
                )
                Icon(Icons.Default.Info,
                    modifier = Modifier.size(16.dp),
                    contentDescription = null)

            }
        }
    }
}

@Composable
fun DisplayImage(
    link: String,
    categoryName: String,
    viewModel: HomeViewModel = viewModel()
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(link)
            .transformations(RoundedCornersTransformation(12F))
            .build()
    )

    Column(
        modifier = Modifier.clickable {
            viewModel.filterByCategory(categoryName)
        }
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(56.dp)
        )
        Text(
            modifier = Modifier.width(60.dp),
            text = categoryName,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }
}
