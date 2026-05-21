package com.ez.weather4you.domain.entity.forecast

import androidx.collection.buildLongLongMap
import kotlinx.datetime.TimeZone

data class Coordinates(
    val latitude: Float,
    val longitude: Float
)