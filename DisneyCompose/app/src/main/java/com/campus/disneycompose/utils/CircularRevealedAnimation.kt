package com.campus.disneycompose.utils

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter

@Composable
internal fun Painter.circularReveal(
    imageBitmap: ImageBitmap,
    durationMs: Int
): Painter {
    // Defines a transition of `CircularRevealState`, and updates the transition when the provided state changes.
    val transitionState = remember { MutableTransitionState(CircularRevealState.None) }
    transitionState.targetState = CircularRevealState.Finished

    // Our actual transition, which reads our transitionState
    val transition = updateTransition(transitionState, label = "")

    val radius: Float by transition.animateFloat(
        transitionSpec = { tween(durationMillis = durationMs) }, label = ""
    ) { state ->
        when (state) {
            CircularRevealState.None -> 0f
            CircularRevealState.Finished -> 1f
        }
    }

    return remember(this) {
        CircularRevealedPainter(
            imageBitmap = imageBitmap,
            painter = this
        )
    }.also {
        it.radius = radius
    }
}

/**
 * CircularRevealState is state of transition for clipping circle to reveal an image
 * depending on its state.
 */
internal enum class CircularRevealState {
    /** animation is not started. */
    None,

    /** animation is finished. */
    Finished
}
