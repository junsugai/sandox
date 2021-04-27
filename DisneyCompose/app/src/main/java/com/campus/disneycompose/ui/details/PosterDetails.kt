package com.campus.disneycompose.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.ui.custom.ImageBalloonAnchor
import com.campus.disneycompose.ui.main.MainViewModel
import com.campus.disneycompose.utils.NetworkImage

@Composable
fun PosterDetails(
    viewModel: MainViewModel,
    pressOnBack: () -> Unit
) {
    val details: Poster? by viewModel.posterDetails.observeAsState()

    details?.let { poster ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colors.background)
                .fillMaxHeight()
        ) {
            ConstraintLayout {
                val (arrow, image, title, content) = createRefs()
                NetworkImage(
                    url = poster.poster,
                    modifier = Modifier
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                        }
                        .fillMaxWidth()
                        .aspectRatio(0.85f),
                    circularRevealedEnabled = true
                )
                Text(
                    text = poster.name,
                    style = MaterialTheme.typography.h1,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(image.bottom)
                        }
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp)
                )
                Text(
                    text = poster.description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .constrainAs(content) {
                            top.linkTo(title.bottom)
                        }
                        .padding(16.dp)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(arrow) {
                            top.linkTo(parent.top)
                        }
                        .padding(12.dp)
                        .clickable(onClick = { pressOnBack() })
                )
                ImageBalloonAnchor(
                    reference = image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.85f),
                    content = poster.name,
                    onClick = { balloon, anchor -> balloon.showAlignBottom(anchor) }
                )
            }
        }
    }
}
