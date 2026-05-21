package com.ez.weather4you.domain.entity.forecast

import kotlin.time.Instant

data class CurrentWeather(
    val lastUpdated: LocalizedTime,
    val temperature: Temperature,
    val feelsLike: Temperature,
    val condition: Condition,
)
