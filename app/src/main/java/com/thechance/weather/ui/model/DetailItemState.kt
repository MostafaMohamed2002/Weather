package com.thechance.weather.ui.model

import androidx.annotation.DrawableRes

data class DetailItemState(
    @DrawableRes val iconRes: Int,
    val value: String,
    val label: String
)