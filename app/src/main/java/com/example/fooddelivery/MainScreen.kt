package com.example.fooddelivery
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fooddelivery.displays.FavoritesScreen
import com.example.fooddelivery.displays.HomeScreen
import com.example.fooddelivery.displays.LoginScreen
import com.example.fooddelivery.displays.ProfileScreen
import com.example.fooddelivery.displays.RestaurantInfo
import com.example.fooddelivery.displays.SignUpScreen
import com.example.fooddelivery.displays.WelcomeScreen
import com.example.fooddelivery.navigation.AppNavGraph
import com.example.fooddelivery.navigation.Screens
import com.example.fooddelivery.navigation.rememberNavigationState
import com.example.fooddelivery.retrofit.data.LoginRequest
import com.example.fooddelivery.retrofit.data.RegisterRequest
import com.example.fooddelivery.ui.theme.FoodDeliveryTheme

import com.example.fooddelivery.ui.theme.TextRed
import com.example.fooddelivery.ui.theme.getDarkTheme
import com.example.fooddelivery.ui.theme.getLightTheme
import com.example.fooddelivery.viewmodels.MainScreenVM

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainScreen(){
    val mainScreenViewModel:MainScreenVM = viewModel()
    val registrationState = mainScreenViewModel.registrationState.collectAsState()
    val loginState = mainScreenViewModel.loginState.collectAsState()
    val navigationState = rememberNavigationState()
    val bottomBarVisibility = mainScreenViewModel.bottomBarVisibilityState.collectAsState()
    val isDarkTheme = mainScreenViewModel.isDarkThemeState.collectAsState()


    FoodDeliveryTheme (
        darkTheme = isDarkTheme.value
    ){
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
//            topBar = {
//                if (topAppBarVisibility.value) {
//                    TopAppBar(
//                        actions = {
//                            Icon(
//                                modifier = Modifier.size(25.dp),
//                                imageVector = Icons.Default.ArrowDropDown,
//                                contentDescription = null
//                            )
//                        },
//                        navigationIcon = {
//                            Icon(
//                                modifier = Modifier.size(25.dp),
//                                painter = painterResource(R.drawable.positions_icon),
//                                contentDescription = null,
//                                tint = Color.Red
//
//                            )
//                        },
//                        title = {
//                            Column(
//                            ) {
//                                Box(
//                                ) {
//                                    Text(
//                                        text = "ВАША ЛОКАЦИЯ",
//                                        color = TextRed,
//                                        fontWeight = FontWeight.W400,
//                                        fontSize = 14.sp
//                                    )
//                                }
//                                Box(
//                                ) {
//                                    Text(
//                                        text = "Москва, Красная Площадь",
//                                        color = MaterialTheme.colorScheme.onBackground,
//                                        fontWeight = FontWeight.W400,
//                                        fontSize = 14.sp
//                                    )
//                                }
//                            }
//                        }
//                    )
//                }
//
//            },
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
                        onSignInButtonClick = {userEmailRequest, userPasswordRequest->
                            mainScreenViewModel.loginUser(
                                LoginRequest(
                                    email = userEmailRequest,
                                    password = userPasswordRequest
                                )
                            )
                        },
                        onSignUpButtonClick = {
                            navigationState.navigateTo(Screens.SignUpScreen.route)
                        }
                    )
                    LaunchedEffect(loginState.value) {
                        if (loginState.value) {
                            navigationState.navigateTo(Screens.HomeScreen.route)
                        }
                    }
                },
                signUpScreenContent = {
                    SignUpScreen(
                        onSignUpClick = {username, email, password->
                            mainScreenViewModel.registerUser(
                                RegisterRequest(
                                    username = username,
                                    email = email,
                                    password = password
                                )
                            )
                        }
                    )
                    LaunchedEffect(registrationState.value) {
                        if (registrationState.value) {
                            navigationState.navigateTo(Screens.LoginScreen.route)
                        }
                    }
                },
                homeScreenContent = {
                    HomeScreen(it,
                        clickedRestaurant = {clickedRestaurant->
                        navigationState.navigateToInfo(clickedRestaurant)
                        })
                    mainScreenViewModel.setBottomBarVisibility(true)
                },
                restaurantInfoContent = {restaurant->
                    RestaurantInfo(restaurant = restaurant)
                },
                profileScreenContent = {
                    ProfileScreen(it){
                        mainScreenViewModel.setDarkThemeState(!isDarkTheme.value)
                    }
                    mainScreenViewModel.setBottomBarVisibility(true)
                },
                favoritesScreenContent = {
                    FavoritesScreen(it)
                    mainScreenViewModel.setBottomBarVisibility(true)
                }
            )
        }
    }
}
