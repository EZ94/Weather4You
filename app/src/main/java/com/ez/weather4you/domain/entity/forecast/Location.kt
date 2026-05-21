package com.ez.weather4you.domain.entity.forecast

import com.ez.weather4you.domain.entity.forecast.Coordinates
import kotlinx.datetime.TimeZone

data class Location(
    val name: String,
    val region: String?,
    val country: String,
    val coordinates: Coordinates,
    val id: Int
)

fun Location.isCurrent() = id == 0