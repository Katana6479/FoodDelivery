package com.example.fooddelivery.displays

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.fooddelivery.R
import com.example.fooddelivery.retrofit.data.Restaurant
import com.example.fooddelivery.ui.theme.TextRed


@Composable
fun RestaurantInfo(
    restaurant: Restaurant
){
    val cardImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(restaurant.link)
            .transformations(RoundedCornersTransformation(12F))
            .build()
    )
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, bottom = 75.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item(){
            Image(
                painter = cardImagePainter,
                modifier = Modifier
                    .size(328.dp,211.dp),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        item(){
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = restaurant.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = restaurant.category
            )
            Spacer(modifier = Modifier.height(4.dp)
            )
        }
        item {
            RestaurantRating(4)
            Spacer(modifier = Modifier.height(4.dp)
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp,end = 30.dp),
                text = "Описание",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp,end = 30.dp),
                text = restaurant.description
            )

        }
    }
}
@Composable
fun RestaurantRating(
    restaurantRating:Int
){
    Row {
        for (i in 1..restaurantRating){
            Icon(modifier =Modifier
                .size(35.dp,35.dp),
                painter = painterResource(R.drawable.ranking),
                contentDescription = null)
        }

    }
}


