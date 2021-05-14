package com.example.jetpackcomposedemo.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposedemo.MainViewModel
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.navigation.Screens
import com.example.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme
import kotlinx.coroutines.launch

@Composable
fun Notification(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.HomeScreens.Notification)

    val scrollState = rememberScrollState()

    // Coroutine Scope
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        Text(text = "Notification Screen.", style = MaterialTheme.typography.h4)

        Spacer20()
        SimpleText()

        Spacer20()
        Text(
            text = "Text Compose",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer20()
        SimpleTextField()

        Spacer20()
        SimpleImage()

        Spacer20()
        SimpleCheckBox()

        Spacer20()
        SimpleLinearProgressIndicator()

        Spacer20()
        Button(onClick = {
            coroutineScope.launch {
                // ScrollState の操作は全て suspend method なので coroutine scope が必須
                scrollState.scrollTo(0)
            }
        }) {
            Text("Scroll to Top")
        }

        Spacer(Modifier.height(100.dp))
    }
}

@Composable
fun Spacer20() {
    Spacer(
        Modifier.height(20.dp)
    )
}

@Composable
fun SimpleText() {
    Text(text = "Compose Material")
}

@Composable
fun SimpleTextField() {
    // 実際には ViewModel にもたせる
    var value by remember { mutableStateOf("Please Editing Text") }

    TextField(
        value = value,
        onValueChange = { value = it },
        label = { Text("TextFieldCompose : Enter text") },
        maxLines = 1,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun SimpleImage() {
    Column {
        Text("Image Compose")
        Image(
            painter = painterResource(id = R.drawable.logo_bizreach),
            contentDescription = "",
        )
        Spacer20()

        Text("Image Compose - circle shape")
        Image(
            painter = painterResource(id = R.drawable.logo_bizreach),
            contentDescription = "",
            Modifier
                .background(Color.Gray)
                .padding(20.dp)
                .clip(CircleShape)
        )

        Spacer20()
        Text("Image Compose - circle shape")
        Spacer20()
        Image(
            painter = painterResource(id = R.drawable.logo_bizreach),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape)
        )
    }
}

@Composable
fun SimpleCheckBox() {
    val checkedState = remember { mutableStateOf(true) }
    Text("Checkbox Compose")
    Spacer20()
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}


@Composable
fun SimpleLinearProgressIndicator() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("ProgressIndicator Compose")
        Spacer20()
        LinearProgressIndicator()
        Spacer20()
        CircularProgressIndicator()
    }
}

@Composable
@Preview
fun PreviewNotificationScreen() {
    JetpackComposeDemoTheme {
        Column(modifier = Modifier.background(Color.White)) {
            Notification(
                viewModel = MainViewModel()
            )
        }
    }
}
