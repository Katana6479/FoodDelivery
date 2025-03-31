package com.example.fooddelivery.Screens

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.fooddelivery.viewmodels.HomeViewModel
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.ColorYellow
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues
){
    val viewModel: HomeViewModel = viewModel()
    val listState = rememberLazyListState()
    LazyColumn (
        state = listState,
        modifier = Modifier.
        fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            SearchField(
                viewModel
            )
        }
        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                DisplayImage(
                    link = "https://eda.yandex/images/1380157/fd0b8c91c2c2a137020053cb3c35c37c-1100x825.jpg",
                    categoryName = "Бургеры"
                )
                DisplayImage(
                    link = "https://eda.yandex/images/3377781/0bd6643e4c33621295ef9862e55f95d7-1100x825.jpg",
                    categoryName = "Суши"
                )
                DisplayImage(
                    link = "https://cdn.kobo.com/book-images/cccc79b9-58cc-488a-a2a0-6db53b84114f/1200/1200/False/the-healthy-90.jpg",
                    categoryName = "Рыба"
                )
                DisplayImage(
                    link = "https://images.deliveryhero.io/image/fd-pk/LH/lbex-hero.jpg",
                    categoryName = "Пицца"
                )
            }
        }
        items(10){
            RestaurantCard(
                link = "https://s3-alpha-sig.figma.com/img/9fb1/7973/baf6df6d95b6eec91309822538dce32c?Expires=1743379200&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=IXYixta32L6Es7ajoPMbwcjiWlEJaX0s5Rx9e1iLodXwpEthBg6E4Du0bC-S5kzXg8cZzvlxXLOR5izYOkgTF6e3RD3DcIfEudHxGbDZoEHLRB0uMffyO4l2cBsVXwhfHz8TZ23cWbSShvnIpwzKp3URUO1hUbyeZR1TRV7Zb4uaxc~PX7U2m7i2ET6FnUv9RhBuFxwNp0pvDgBIijDaisoVC12VTCqRLxhOCULay3Tu8fKAkNbXQvldfVd8PkjX8b2C8tFN~gQQJj6vhO2sIiZ~QEZ-2wfbYJXj7QssIL~VdHIpNmn1ohIBUJIA1fiHlaKtr-7zwUI73lKuWoYYEA__"
            )
        }

    }
}

@Composable
fun SearchField(
    viewModel: HomeViewModel
    ){
    val textFieldState = viewModel.textFieldState.collectAsState()
    val clearButtonVisibilityState = viewModel.clearButtonState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val suggestions = viewModel.searchHistoryList.collectAsState()
    val showSuggestions = remember { mutableStateOf(false) }
    Box(){
        OutlinedTextField(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = textFieldState.value,
            placeholder = {
                Text(text = "Найдите ресторан")
            },
            singleLine = true,
            onValueChange = {newValue->
                viewModel.updateTextFieldState(newValue)
                viewModel.updateClearButtonState(true)
                showSuggestions.value = newValue.isNotEmpty()
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = LoginFieldColor,
                unfocusedBorderColor = LoginFieldBorderColor,
                unfocusedTextColor = LoginFieldTextColor
            ),
            trailingIcon = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(textFieldState.value !=""&& clearButtonVisibilityState.value){
                    Text(
                        text = "Сброс",
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .clickable(
                                onClick = {
                                    viewModel.updateTextFieldState("")
                                    keyboardController?.hide()
                                    showSuggestions.value = false
                                }
                            )
                    )
                      }else{
                       Text(text="")
                      }
                    IconButton (
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(30)),
                        colors = IconButtonDefaults.iconButtonColors().copy(
                            containerColor = ColorYellow
                        ),
                        onClick = {}
                    ){
                        Icon(painterResource(R.drawable.search), contentDescription = null)
                    }
                }
            },
            shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
        )
        DropdownMenu(
            expanded = showSuggestions.value,
            onDismissRequest = { showSuggestions.value = false },
            modifier = Modifier.fillMaxWidth(),
            properties = PopupProperties(focusable = false)
        ) {
            suggestions.value.forEach { suggestion ->
                DropdownMenuItem(
                    text = { Text(text = suggestion) },
                    onClick = {
                        viewModel.updateTextFieldState(suggestion)
                        showSuggestions.value = false
                    }
                )
            }
        }
    }
}
@Composable
fun RestaurantCard(
    link:String
){
    val cardImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(link)
            .transformations(RoundedCornersTransformation(12F))
            .build()
    )
    Spacer(Modifier.height(16.dp))
    Card (
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
                text = "Золотой ресторан",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 2.dp),
                text = "Китайский",
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
                    text = "4.0"
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
    link:String,
    categoryName: String
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(link)
            .transformations(RoundedCornersTransformation(12F))
            .build()
    )
    Column {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
        )
        Text(
            modifier = Modifier
                .width(58.dp),
            text = categoryName,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }

}
@Preview
@Composable
fun PreviewHome(){
    HomeScreen(paddingValues = PaddingValues(16.dp))
}