package com.ez.weather4you.domain.entity.forecast

import kotlin.time.Instant

data class HourForecast(
    val temperature: Temperature,
    val feelsLike: Temperature,
    val condition: Condition,
    val time: LocalizedTime
)
