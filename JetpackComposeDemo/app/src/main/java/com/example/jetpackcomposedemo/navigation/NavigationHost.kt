package com.example.jetpackcomposedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.jetpackcomposedemo.MainViewModel
import com.example.jetpackcomposedemo.ui.screen.MyNetwork
import com.example.jetpackcomposedemo.ui.screen.Notification

@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.HomeScreens.Favorite.route
    ) {
        composable(Screens.HomeScreens.Favorite.route) { Favorite(viewModel = viewModel) }
        composable(Screens.HomeScreens.Notification.route) { Notification(viewModel = viewModel) }
        composable(Screens.HomeScreens.MyNetwork.route) { MyNetwork(viewModel = viewModel) }
    }
}