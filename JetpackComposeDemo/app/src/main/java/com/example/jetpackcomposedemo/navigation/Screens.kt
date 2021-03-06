package com.example.jetpackcomposedemo.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jetpackcomposedemo.MainViewModel


sealed class Screens(val route: String, val title: String) {

    sealed class HomeScreens(
        route: String,
        title: String,
        val icon: ImageVector
    ) : Screens(
        route,
        title
    ) {
        object Favorite : HomeScreens("favorite", "Favorite", Icons.Filled.Favorite)
        object Notification :
            HomeScreens("notification", "Notification", Icons.Filled.Notifications)

        object MyNetwork : HomeScreens("network", "MyNetwork", Icons.Filled.Person)
    }
}

val screensInHomeFromBottomNav = listOf(
    Screens.HomeScreens.Favorite,
    Screens.HomeScreens.Notification,
    Screens.HomeScreens.MyNetwork
)

@Composable
fun Favorite(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.HomeScreens.Favorite)
    val clickCount by viewModel.clickCount.observeAsState()
    var click = clickCount ?: 0
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Favorite.", style = MaterialTheme.typography.h4)
        Button(
            onClick = {
                click++
                viewModel.updateClick(click)
            }
        ) {
            Text("clicked: $click")
        }
    }
}
