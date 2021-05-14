package com.example.jetpackcomposedemo.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposedemo.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun AppScaffold() {
    val viewModel: MainViewModel = viewModel()
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.observeAsState()

    val topBar: @Composable () -> Unit = {
        TopBar(
            title = currentScreen!!.title,
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = {
                scope.launch {
                    // Navigation Icon を設定した場合、アイコンをクリックすると呼ばれるコールバック
                }
            }
        )
    }

    val bottomTab: @Composable () -> Unit = {
        if (currentScreen is Screens.HomeScreens) {
            BottomBar(
                navController = navController,
                screens = screensInHomeFromBottomNav
            )
        }
    }

    Scaffold(
        topBar = {
            topBar()
        },
        bottomBar = {
            bottomTab()
        },
        scaffoldState = scaffoldState
    ) {
        NavigationHost(navController = navController, viewModel = viewModel)
    }
}