package com.example.jetpackcomposedemo.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        // Navigation Icon が欲しい場合は下記のコメントを有効にする
//        navigationIcon = {
//            IconButton(onClick = { onButtonClicked() }) {
//                Icon(buttonIcon, contentDescription = "")
//            }
//        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}
