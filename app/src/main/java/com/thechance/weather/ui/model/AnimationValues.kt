package com.thechance.weather.ui.model

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.Dp

data class AnimationValues(
    val boxHeight: Dp,
    val imageSize: Dp,
    val imageAlignment: Alignment,
    val weatherAlignment: Alignment,
    val weatherAlpha: Float
)