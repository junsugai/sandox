package com.campus.disneycompose.utils

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

@Composable
fun <T : Any> ImageLoad(
    imageRequest: T,
    executeImageRequest: suspend () -> Flow<ImageLoadState>,
    modifier: Modifier = Modifier,
    content: @Composable (imageState: ImageLoadState) -> Unit
) {
    var state by remember(imageRequest) { mutableStateOf<ImageLoadState>(ImageLoadState.None) }
    LaunchedEffect(imageRequest) {
        executeImageLoading(
            executeImageRequest
        ).collect {
            state = it
        }
    }
    BoxWithConstraints(modifier) {
        content(state)
    }
}

private suspend fun executeImageLoading(
    executeImageRequest: suspend () -> Flow<ImageLoadState>
) = flow {
    // execute imager loading
    emitAll(executeImageRequest())
}.catch {
    // emit a failure loading state
    emit(ImageLoadState.Failure(null))
}.distinctUntilChanged().flowOn(Dispatchers.IO)
