package com.example.fooddelivery.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.example.fooddelivery.R
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    paddingValues: PaddingValues
){
        val text = "Search restaurants"
        LazyColumn (
            modifier = Modifier.
            fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    value = text,
                    onValueChange = {},
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = LoginFieldColor,
                        unfocusedBorderColor = LoginFieldBorderColor,
                        unfocusedTextColor = LoginFieldTextColor
                    ),
                    leadingIcon = { Icon(painterResource(R.drawable.search), contentDescription = null)},
                    shape = MaterialTheme.shapes.medium.copy(CornerSize(20.dp))
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
                        categoryName = "Burgers"
                    )
                    DisplayImage(
                        link = "https://eda.yandex/images/3377781/0bd6643e4c33621295ef9862e55f95d7-1100x825.jpg",
                        categoryName = "Sushi"
                    )
                    DisplayImage(
                        link = "https://cdn.kobo.com/book-images/cccc79b9-58cc-488a-a2a0-6db53b84114f/1200/1200/False/the-healthy-90.jpg",
                        categoryName = "Fish"
                    )
                    DisplayImage(
                        link = "https://s3-alpha-sig.figma.com/img/0b1f/dca5/91958ec12f346cb35741dc7c7d1c69f7?Expires=1742169600&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=jC5WfW6-hTq4Ie~D8sI3yh5iZf9eBLaZYIgx7lxWXXmP~aU0ATUIvOJcGDfh~pUHoGh0l-1zcbqsPFXh05NXWukuwWOOf0E6qUVmex2Yc9hW7BUceJ6jbe8LOH0t0axceNDkOwc2NgLmnK0sInxAcrr3M2i2xGofRqUluNjiqOAQHWtBVwAjqSHlIB2iB3mMNpbJUPCOk2hUMzwL3wycJPQZyqf4xVIDyCnHvkOPH9olhKW8fIRqtILxRTAKekrUeGx1Cl-CSJ1mdbu2XZBANPgdyO6pyQneZ5KW-ZF2dhCK7m9LDtapQBOxCoeAmq-51wACVT8etxlXtA4he2b72A__",
                        categoryName = "Pizza"
                    )
                }
            }
            items(10){
                RestaurantCard(
                    link = "https://s3-alpha-sig.figma.com/img/9fb1/7973/baf6df6d95b6eec91309822538dce32c?Expires=1742169600&Key-Pair-Id=APKAQ4GOSFWCW27IBOMQ&Signature=WOcpyBJvRHJnnWevp2yv8m-mP1Z3ju908R-V-R2nlUSAXkvFrMHgrxfm4vn1z5y6b5DIC-Mnktqh4wfyGt3-ZxcXwZ4YZQgGHBA7Ch-pCyqc~rm63h417Y~TGe2wFA8Zr2pCjT5VK9wdeYBBVBHwEZ342QdtmINdREqF4KrgN~CyhlOxz-A3Nb7742yPpfzoIOZS4YWVIGea2cMDLMAXkngXkAKraR9xnxpc8XZBU1Yi~vBN3IvF9wBY004L29Xe2b~HEEOJtRV9iPnOt3eLefOKYN4t6oUzVqpg1Rm9ly22iPiklqhI~UIrwHGIx745dAgfKUwnd~5rHiClm7J4Qg__"
                )
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
                text = "Golden restaurant",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            Text(
                modifier = Modifier
                    .padding(start = 2.dp),
                text = "Chinese",
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
                    text = "45 min"
                )
                Icon(painter = painterResource(R.drawable.service_time),
                    modifier = Modifier.size(16.dp),
                    contentDescription = null)
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Text(
                    text = "Reviews"
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
                .width(56.dp),
            text = categoryName,
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
    }

}