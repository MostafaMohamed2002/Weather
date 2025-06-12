package com.thechance.weather.presentation.model

import androidx.annotation.DrawableRes

data class DetailItemState(
    @DrawableRes val iconRes: Int,
    val value: String,
    val label: String
)