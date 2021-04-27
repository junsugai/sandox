package com.campus.disneycompose.ui.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.campus.disneycompose.model.Poster
import com.campus.disneycompose.ui.theme.DisneyComposeTheme
import com.campus.disneycompose.utils.NetworkImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun RadioPosters(
    posters: List<Poster>,
    selectPoster: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
            .statusBarsPadding()
            .background(MaterialTheme.colors.background)
    ) {
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(4.dp)
        ) {
            items(
                items = posters,
                itemContent = { poster ->
                    RadioPoster(
                        poster = poster,
                        selectPoster = selectPoster
                    )
                }
            )
        }
    }
}

@Composable
fun RadioPoster(
    poster: Poster,
    selectPoster: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable(
                onClick = { selectPoster(poster.id) }
            ),
        color = MaterialTheme.colors.onBackground,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(8.dp)
        ) {
            val (image, title, content) = createRefs()
            NetworkImage(
                modifier = Modifier
                    .constrainAs(image) {
                        centerVerticallyTo(parent)
                        end.linkTo(title.start)
                    }
                    .height(64.dp)
                    .aspectRatio(1f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp)),
                url = poster.poster
            )
            Text(
                poster.name,
                Modifier
                    .constrainAs(title) {
                        start.linkTo(image.end)
                    }
                    .padding(horizontal = 12.dp),
                Color.Unspecified,
                TextUnit.Unspecified,
                null,
                null,
                null,
                TextUnit.Unspecified,
                null,
                null,
                TextUnit.Unspecified,
                TextOverflow.Ellipsis,
                true,
                1,
                {},
                MaterialTheme.typography.h2
            )
            Text(
                text = poster.playtime,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .constrainAs(content) {
                        start.linkTo(image.end)
                        top.linkTo(title.bottom)
                    }
                    .padding(start = 12.dp, top = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun RadioPosterPreviewLight() {
    DisneyComposeTheme(darkTheme = false) {
        RadioPoster(
            poster = Poster.mock(),
            selectPoster = { }
        )
    }
}

@Preview
@Composable
fun RadioPosterPreviewDark() {
    DisneyComposeTheme(darkTheme = true) {
        RadioPoster(
            poster = Poster.mock(),
            selectPoster = { }
        )
    }
}
