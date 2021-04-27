package com.campus.disneycompose.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LifecycleOwner
import coil.ImageLoader
import coil.request.Disposable
import coil.request.ImageRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter


@Composable
fun CoilImage(
    imageModel: Any,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    colorFilter: ColorFilter? = null,
    shimmerParams: ShimmerParams,
    error: ImageBitmap? = null,
) {
    CoilImage(
        imageModel = imageModel,
        context = context,
        lifecycleOwner = lifecycleOwner,
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        circularRevealedEnabled = circularRevealedEnabled,
        circularRevealedDuration = circularRevealedDuration,
        shimmerParams = shimmerParams,
        failure = {
            error?.let {
                Image(
                    bitmap = it,
                    alignment = alignment,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    colorFilter = colorFilter,
                    alpha = alpha,
                )
            }
        }
    )
}


@Composable
fun CoilImage(
    imageModel: Any,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    alpha: Float = DefaultAlpha,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    colorFilter: ColorFilter? = null,
    placeHolder: ImageBitmap? = null,
    error: ImageBitmap? = null,
) {
    CoilImage(
        imageModel = imageModel,
        context = context,
        lifecycleOwner = lifecycleOwner,
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        circularRevealedEnabled = circularRevealedEnabled,
        circularRevealedDuration = circularRevealedDuration,
        loading = {
            placeHolder?.let {
                Image(
                    bitmap = it,
                    alignment = alignment,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    colorFilter = colorFilter,
                    alpha = alpha
                )
            }
        },
        failure = {
            error?.let {
                Image(
                    bitmap = it,
                    alignment = alignment,
                    contentDescription = contentDescription,
                    contentScale = contentScale,
                    colorFilter = colorFilter,
                    alpha = alpha,
                )
            }
        }
    )
}


@Composable
fun CoilImage(
    imageModel: Any,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    shimmerParams: ShimmerParams,
    success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
    failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
        imageRequest = ImageRequest.Builder(context)
            .data(imageModel)
            .lifecycle(lifecycleOwner)
            .build(),
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
        alignment = alignment,
        contentScale = contentScale,
        contentDescription = contentDescription,
        alpha = alpha,
        colorFilter = colorFilter,
        circularRevealedEnabled = circularRevealedEnabled,
        circularRevealedDuration = circularRevealedDuration,
        shimmerParams = shimmerParams,
        success = success,
        failure = failure
    )
}

@Composable
fun CoilImage(
    imageModel: Any,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    loading: @Composable ((imageState: CoilImageState.Loading) -> Unit)? = null,
    success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
    failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
        imageRequest = ImageRequest.Builder(context)
            .data(imageModel)
            .lifecycle(lifecycleOwner)
            .build(),
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
        alignment = alignment,
        contentScale = contentScale,
        contentDescription = contentDescription,
        alpha = alpha,
        colorFilter = colorFilter,
        circularRevealedEnabled = circularRevealedEnabled,
        circularRevealedDuration = circularRevealedDuration,
        loading = loading,
        success = success,
        failure = failure
    )
}

@Composable
fun CoilImage(
    imageRequest: ImageRequest,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    shimmerParams: ShimmerParams,
    success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
    failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
        request = imageRequest,
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
    ) { imageState ->
        when (val coilImageState = imageState.toCoilImageState()) {
            is CoilImageState.None -> Unit
            is CoilImageState.Loading -> {
                Shimmer(
                    baseColor = shimmerParams.baseColor,
                    highlightColor = shimmerParams.highlightColor,
                    intensity = shimmerParams.intensity,
                    dropOff = shimmerParams.dropOff,
                    tilt = shimmerParams.tilt,
                    durationMillis = shimmerParams.durationMillis
                )
            }
            is CoilImageState.Failure -> failure?.invoke(coilImageState)
            is CoilImageState.Success -> {
                success?.invoke(coilImageState) ?: coilImageState.imageBitmap?.let {
                    CircularRevealedImage(
                        modifier = modifier,
                        bitmap = it,
                        alignment = alignment,
                        contentScale = contentScale,
                        contentDescription = contentDescription,
                        alpha = alpha,
                        colorFilter = colorFilter,
                        circularRevealedEnabled = circularRevealedEnabled,
                        circularRevealedDuration = circularRevealedDuration
                    )
                }
            }
        }
    }
}

@Composable
fun CoilImage(
    imageRequest: ImageRequest,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration,
    loading: @Composable ((imageState: CoilImageState.Loading) -> Unit)? = null,
    success: @Composable ((imageState: CoilImageState.Success) -> Unit)? = null,
    failure: @Composable ((imageState: CoilImageState.Failure) -> Unit)? = null,
) {
    CoilImage(
        request = imageRequest,
        imageLoader = imageLoader,
        modifier = modifier.fillMaxWidth(),
    ) { imageState ->
        when (val coilImageState = imageState.toCoilImageState()) {
            is CoilImageState.None -> Unit
            is CoilImageState.Loading -> loading?.invoke(coilImageState)
            is CoilImageState.Failure -> failure?.invoke(coilImageState)
            is CoilImageState.Success -> {
                success?.invoke(coilImageState) ?: coilImageState.imageBitmap?.let {
                    CircularRevealedImage(
                        modifier = modifier,
                        bitmap = it,
                        alignment = alignment,
                        contentScale = contentScale,
                        contentDescription = contentDescription,
                        alpha = alpha,
                        colorFilter = colorFilter,
                        circularRevealedEnabled = circularRevealedEnabled,
                        circularRevealedDuration = circularRevealedDuration
                    )
                }
            }
        }
    }
}

@Composable
fun CoilImage(
    request: ImageRequest,
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader = LocalCoilProvider.getCoilImageLoader(),
    content: @Composable (imageState: ImageLoadState) -> Unit
) {
    val context = LocalContext.current
    val imageLoadStateFlow =
        remember { MutableStateFlow<ImageLoadState>(ImageLoadState.Loading(0f)) }
    val disposable = remember { mutableStateOf<Disposable?>(null) }

    ImageLoad(
        imageRequest = request,
        executeImageRequest = {
            suspendCancellableCoroutine { cont ->
                disposable.value = imageLoader.enqueue(
                    request.newBuilder(context).target(
                        onSuccess = {
                            imageLoadStateFlow.value =
                                ImageLoadState.Success(it.toBitmap().asImageBitmap())
                        },
                        onError = {
                            imageLoadStateFlow.value =
                                ImageLoadState.Failure(it?.toBitmap()?.asImageBitmap())
                        }
                    ).build()
                )

                cont.resume(imageLoadStateFlow) {
                    // dispose the coil disposable request if cancelled.
                    disposable.value?.dispose()
                }
            }
        },
        modifier = modifier,
        content = content
    )
}

sealed class CoilImageState {

    object None : CoilImageState()
    data class Loading(val progress: Float) : CoilImageState()
    data class Success(val imageBitmap: ImageBitmap?) : CoilImageState()
    data class Failure(val errorDrawable: Drawable?) : CoilImageState()
}

@Suppress("UNCHECKED_CAST")
fun ImageLoadState.toCoilImageState(): CoilImageState {
    return when (this) {
        is ImageLoadState.None -> CoilImageState.None
        is ImageLoadState.Loading -> CoilImageState.Loading(progress)
        is ImageLoadState.Success -> CoilImageState.Success(imageBitmap)
        is ImageLoadState.Failure -> CoilImageState.Failure(data as? Drawable)
    }
}

sealed class ImageLoadState {

    object None : ImageLoadState()

    data class Loading(val progress: Float) : ImageLoadState()

    data class Success(val imageBitmap: ImageBitmap?) : ImageLoadState()

    data class Failure(val data: Any?) : ImageLoadState()
}

@Composable
fun CircularRevealedImage(
    bitmap: ImageBitmap,
    modifier: Modifier = Modifier,
    bitmapPainter: Painter = BitmapPainter(bitmap),
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String?,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    circularRevealedEnabled: Boolean = false,
    circularRevealedDuration: Int = DefaultCircularRevealedDuration
) {
    Image(
        painter = if (circularRevealedEnabled) {
            bitmapPainter.circularReveal(bitmap, circularRevealedDuration)
        } else {
            bitmapPainter
        },
        modifier = modifier,
        alignment = alignment,
        contentDescription = contentDescription,
        contentScale = contentScale,
        colorFilter = colorFilter,
        alpha = alpha
    )
}

/** a definition of the default circular revealed animations duration. */
const val DefaultCircularRevealedDuration = 350

