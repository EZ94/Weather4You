package com.ez.weather4you.domain.entity.forecast

import kotlin.time.Instant

data class DayForecast(
    val time: LocalizedTime,
    val condition: Condition,
    val maxTemperature: Temperature,
    val minTemperature: Temperature
)
