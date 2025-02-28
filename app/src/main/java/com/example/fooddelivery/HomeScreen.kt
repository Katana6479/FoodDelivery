package com.example.fooddelivery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fooddelivery.ui.theme.LoginFieldBorderColor
import com.example.fooddelivery.ui.theme.LoginFieldColor
import com.example.fooddelivery.ui.theme.LoginFieldTextColor
import com.example.fooddelivery.ui.theme.TextRed

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null)
                },
                navigationIcon = {Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(R.drawable.positions_icon),
                    contentDescription = null
                )},
                title = {
                    Column(
                    ){
                        Box(
                        ){
                            Text(
                                text = "YOUR LOCATION",
                                color = TextRed,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp
                            )
                        }
                        Box(
                        ){
                            Text(
                                text = "Moscow, Red Square",
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            val bottomItems = listOf(BottomBarItems.Home,BottomBarItems.Search,BottomBarItems.Profile,BottomBarItems.Favorite)
            BottomAppBar {
                val itemHome = bottomItems.getItemByType(BottomBarItems.Home)
                NavigationBarItem(
                    onClick = {},
                    icon = {Icon(painter = painterResource(itemHome.icon), contentDescription = null)},
                    label = {
                        Text(
                            itemHome.name,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                            },
                    selected = true
                )
                val itemSearch = bottomItems.getItemByType(BottomBarItems.Search)
                NavigationBarItem(
                    onClick = {},
                    icon = {Icon(painter = painterResource(itemSearch.icon), contentDescription = null)},
                    label = {
                        Text(
                            itemSearch.name,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                            },
                    selected = false
                )
                val itemFavorite = bottomItems.getItemByType(BottomBarItems.Favorite)
                NavigationBarItem(
                    onClick = {},
                    icon = {Icon(painter = painterResource(itemFavorite.icon), contentDescription = null)},
                    selected = false,
                    label = {
                        Text(
                            itemFavorite.name,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                            },
                )
                val itemProfile = bottomItems.getItemByType(BottomBarItems.Profile)
                NavigationBarItem(
                    onClick = {},
                    icon = { Icon(painter = painterResource(itemProfile.icon), contentDescription = null) },
                    selected = false,
                    label = {
                        Text(
                            itemProfile.name,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp
                        )
                            },
                )
            }
        }
    ) {
        val text = "Search restaurants"
        LazyColumn (modifier = Modifier.fillMaxSize().padding(it)){
            item {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .padding(it),
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
                        .fillMaxWidth()
                ){
                }
            }
        }

    }
}
fun List<BottomBarItems>.getItemByType(
    type:BottomBarItems
):BottomBarItems{
    return type
}