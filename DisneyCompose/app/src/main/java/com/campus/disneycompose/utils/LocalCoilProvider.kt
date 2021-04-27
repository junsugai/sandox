package com.campus.disneycompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.imageLoader

val LocalCoilImageLoader = staticCompositionLocalOf<ImageLoader?> { null }

internal object LocalCoilProvider {
    @Composable
    fun getCoilImageLoader(): ImageLoader {
        return LocalCoilImageLoader.current ?: LocalContext.current.imageLoader
    }
}
