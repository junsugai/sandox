package com.campus.disneycompose.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.ui.custom.StaggeredVerticalGrid
import com.campus.disneycompose.ui.theme.DisneyComposeTheme
import com.campus.disneycompose.utils.NetworkImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun HomePosters(
    posters: List<Poster>,
    selectPoster: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            posters.forEach { poster ->
                HomePoster(poster = poster, selectPoster = selectPoster)
            }
        }
    }
}

@Composable
fun HomePoster(
    poster: Poster,
    selectPoster: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clickable(
                onClick = { selectPoster(poster.id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout {
            val (image, title, content) = createRefs()
            NetworkImage(
                url = poster.poster,
                modifier = Modifier
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
                    .aspectRatio(0.8f)
            )
            Text(
                text = poster.name,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(title) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
                    .padding(8.dp)
            )
            Text(
                text = poster.playtime,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .constrainAs(content) {
                        centerHorizontallyTo(parent)
                        top.linkTo(title.bottom)
                    }
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp)
            )
        }
    }
}

@Preview
@Composable
fun HomePosterPreviewLight() {
    DisneyComposeTheme(darkTheme = false) {
        HomePoster(
            poster = Poster.mock(),
            selectPoster = {}
        )
    }
}

@Preview
@Composable
fun HomePosterPreviewDark() {
    DisneyComposeTheme(darkTheme = true) {
        HomePoster(
            poster = Poster.mock(),
            selectPoster = {}
        )
    }
}
