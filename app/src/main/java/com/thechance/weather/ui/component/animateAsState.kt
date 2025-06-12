package com.thechance.weather.ui.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.unit.dp
import com.thechance.weather.ui.model.AnimationValues

@Composable
fun animateAsState(isCollapsed: Boolean): AnimationValues {
    val boxHeight by animateDpAsState(
        targetValue = if (isCollapsed) 143.dp else 360.dp,
        label = "boxHeight"
    )
    val imageSize by animateDpAsState(
        targetValue = if (isCollapsed) 124.dp else 210.dp,
        label = "imageSize"
    )
    val weatherAlpha by animateFloatAsState(
        targetValue = if (isCollapsed) 0f else 1f,
        label = "weatherAlpha"
    )

    val imageHorizontalBias by animateFloatAsState(
        targetValue = if (isCollapsed) -1f else 0f,
        label = "imageHorizontalBias"
    )
    val imageVerticalBias by animateFloatAsState(
        targetValue = if (isCollapsed) 0f else -1f,
        label = "imageVerticalBias"
    )

    val weatherHorizontalBias by animateFloatAsState(
        targetValue = if (isCollapsed) 1f else 0f,
        label = "weatherHorizontalBias"
    )
    val weatherVerticalBias by animateFloatAsState(
        targetValue = if (isCollapsed) 0f else 1f,
        label = "weatherVerticalBias"
    )

    return AnimationValues(
        boxHeight = boxHeight,
        imageSize = imageSize,
        imageAlignment = BiasAlignment(
            horizontalBias = imageHorizontalBias,
            verticalBias = imageVerticalBias
        ),
        weatherAlignment = BiasAlignment(
            horizontalBias = weatherHorizontalBias,
            verticalBias = weatherVerticalBias
        ),
        weatherAlpha = weatherAlpha
    )
}