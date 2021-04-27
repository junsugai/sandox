package com.campus.disneycompose.utils

import androidx.compose.ui.graphics.Color

internal const val DefaultShimmerIntensity = 0f

internal const val DefaultShimmerDropOff = 0.5f

internal const val DefaultShimmerTilt = 20f

internal const val DefaultDurationMillis = 650

data class ShimmerParams(
    val baseColor: Color,
    val highlightColor: Color,
    val intensity: Float = DefaultShimmerIntensity,
    val dropOff: Float = DefaultShimmerDropOff,
    val tilt: Float = DefaultShimmerTilt,
    val durationMillis: Int = DefaultDurationMillis
)

