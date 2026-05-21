package com.ez.weather4you.domain.entity

data class UserPreferences(
    val locationPermissionRequestCounter: Int,
    val temperatureUnit: TemperatureUnit,
    val hourUnit: HourUnit,
    val speedUnit: SpeedUnit
)
