package com.example.jetpackcomposedemo.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposedemo.MainViewModel
import com.example.jetpackcomposedemo.R
import com.example.jetpackcomposedemo.navigation.Screens
import com.example.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme


@Composable
fun MyNetwork(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.HomeScreens.MyNetwork)

    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(state = scrollState)
    ) {
        Text(text = "MyNetwork.", style = MaterialTheme.typography.h4)

        ScreenDivider()
        SimpleIndicator()

        ScreenDivider()
        SimpleDropdown()

        ScreenDivider()
        SimpleRadioButton()

        ScreenDivider()
        SimpleCard()

        ScreenDivider()
        SimpleConstraint()

        ScreenDivider()
        SimpleHorizontalScroll()

        Spacer(modifier = Modifier.height(100.dp))


    }
}

@Composable
fun ScreenDivider() {
    Column() {
        Spacer20()
        Divider()
    }
}

@Composable
fun SimpleIndicator() {

    Column {
        Text("Slider Compose")
        Spacer20()
        var sliderPosition by remember { mutableStateOf(0f) }
        Text(
            text = "Slider Position = $sliderPosition"
        )
        Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
    }
}

@Composable
fun SimpleDropdown() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("ListA", "TypeB", "ModuleC", "RankD")
    val disabledValue = "TypeB"
    var selectedIndex by remember { mutableStateOf(0) }

    Text("Dropdown Compose")
    Spacer20()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        Text(
            items[selectedIndex],
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .background(
                    Color.Gray
                )
                .padding(8.dp),

            textAlign = TextAlign.Start,
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.LightGray
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}


@Composable
fun SimpleRadioButton() {

    // RadioButton の種類
    val radioOptions = listOf("RadioItem 1", "RadioItem 2", "RadioItem 3")

    // 選択状態のプロパティ
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }

    Text("RadioButton Compose")
    Spacer20()
    Column {
        radioOptions.forEach { text ->

            // 横向きに RadioButton とテキストを並べる
            // かつ Clickable にしておく
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 12.dp)
            ) {
                // ラジオボタン
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                // ラジオボタンにはテキストがないので自前で設定する
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SimpleCard() {

    Spacer20()
    Text("Card Layout Compose")
    Spacer20()

    // Elevation をサポートしているのはカードだけ.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { /* click */ },
        elevation = 12.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                buildAnnotatedString {
                    append("i am  ")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.W900, color = Color.Cyan)
                    ) {
                        append("Card Compose (Material)")
                    }
                }
            )

            Text("カード")
        }
    }
}

@Composable
fun SimpleConstraint() {

    Column {
        Text("Constraint Compose")
        Spacer20()
        ConstraintLayout {
            val (button, text) = createRefs()

            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.constrainAs(button) {
                    top.linkTo(parent.top, margin = 16.dp)
                }
            ) {
                Text("Button")
            }

            Text("Text", Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
                start.linkTo(button.end, margin = 16.dp)
            })
        }
    }
}

@Composable
fun SimpleHorizontalScroll() {
    val horizontalScrollState = rememberScrollState()
    val cardList =
        mutableListOf<String>("Card-1", "Card-2", "Card-3", "Card-4", "Card-5", "Card-6", "Card-7")

    Text("Row Compose : Horizontal Scroll")
    Spacer20()
    Row(
        Modifier.horizontalScroll(horizontalScrollState)
    ) {

        cardList.forEach {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .width(120.dp)
                    .clickable { /* do something */ },
                elevation = 12.dp,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer20()
                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_bizreach
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .size(80.dp)
                            .border(1.dp, Color.LightGray, RectangleShape)
                    )
                    Text(
                        text = it,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }

}

@Composable
@Preview
fun PreviewMyNetwork() {
    JetpackComposeDemoTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            MyNetwork(
                viewModel = MainViewModel()
            )
        }
    }
}