package com.example.fitnessdays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(
    val dayNumber: Number,
    @param:StringRes val titleRes: Int,
    @param:StringRes val descriptionRes: Int,
    @param:DrawableRes val imageRes: Int,
)
