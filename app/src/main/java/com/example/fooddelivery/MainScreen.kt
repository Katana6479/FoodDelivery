package com.example.fooddelivery
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fooddelivery.Screens.FavoritesScreen
import com.example.fooddelivery.Screens.HomeScreen
import com.example.fooddelivery.Screens.LoginScreen
import com.example.fooddelivery.Screens.ProfileScreen
import com.example.fooddelivery.Screens.SignUpScreen
import com.example.fooddelivery.Screens.WelcomeScreen
import com.example.fooddelivery.navigation.AppNavGraph
import com.example.fooddelivery.navigation.Screens
import com.example.fooddelivery.navigation.rememberNavigationState
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme

import com.example.fooddelivery.ui.theme.TextRed
import com.example.fooddelivery.ui.theme.getDarkTheme
import com.example.fooddelivery.ui.theme.getLightTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen(){
    val navigationState = rememberNavigationState()
    val topAppBarVisibility = remember{ mutableStateOf(false) }
    val bottomBarVisibility = remember { mutableStateOf(false) }
    val isDarkTheme = remember { mutableStateOf(false) }

    val colorScheme = if (isDarkTheme.value) getDarkTheme else getLightTheme

    FoodDeliveryTheme (
        darkTheme = isDarkTheme.value
    ){
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                if (topAppBarVisibility.value) {
                    TopAppBar(
                        actions = {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier.size(25.dp),
                                painter = painterResource(R.drawable.positions_icon),
                                contentDescription = null,
                                tint = Color.Red

                            )
                        },
                        title = {
                            Column(
                            ) {
                                Box(
                                ) {
                                    Text(
                                        text = "ВАША ЛОКАЦИЯ",
                                        color = TextRed,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                }
                                Box(
                                ) {
                                    Text(
                                        text = "Москва, Красная Площадь",
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    )
                }

            },
            bottomBar = {
                NavigationBar {
                    val bottomItems =
                        listOf(BottomBarItems.Home, BottomBarItems.Favorite, BottomBarItems.Profile)
                    val navBackStack =
                        navigationState.navHostController.currentBackStackEntryAsState()
                    bottomItems.forEach { bottomItem ->
                        if (bottomBarVisibility.value) {
                            val selected = navBackStack.value?.destination?.hierarchy?.any {
                                it.route == bottomItem.screen.route
                            } ?: false
                            NavigationBarItem(
                                onClick = {
                                    if (!selected) {
                                        navigationState.navigateTo(bottomItem.screen.route)
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(bottomItem.icon),
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(
                                        bottomItem.name,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp
                                    )
                                },
                                selected = selected
                            )
                        }
                    }
                }
            }
        ) {
            AppNavGraph(
                navHostController = navigationState.navHostController,
                welcomeScreenContent = {
                    WelcomeScreen {
                        navigationState.navigateTo(Screens.LoginScreen.route)
                    }
                },
                loginScreenContent = {
                    LoginScreen(
                        onSignInButtonClick = {
                            navigationState.navigateTo(Screens.HomeScreen.route)
                        },
                        onSignUpButtonClick = {
                            navigationState.navigateTo(Screens.SignUpScreen.route)
                        }
                    )
                },
                signUpScreenContent = {
                    SignUpScreen(
                        onSignUpClick = {
                            navigationState.navigateTo(Screens.HomeScreen.route)
                        }
                    )
                },
                homeScreenContent = {
                    HomeScreen(it)
                    topAppBarVisibility.value = true
                    bottomBarVisibility.value = true
                },
                profileScreenContent = {
                    ProfileScreen(it){
                        isDarkTheme.value=!isDarkTheme.value
                    }
                    topAppBarVisibility.value = true
                    bottomBarVisibility.value = true
                },
                favoritesScreenContent = {
                    FavoritesScreen(it)
                    topAppBarVisibility.value = true
                    bottomBarVisibility.value = true
                }
            )
        }
    }
}
