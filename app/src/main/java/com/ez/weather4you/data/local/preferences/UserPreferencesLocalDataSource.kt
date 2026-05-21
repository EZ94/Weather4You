package com.ez.weather4you.data.local.preferences

import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.SpeedUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.entity.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesLocalDataSource {
    val userPreferences: Flow<UserPreferences>

    suspend fun increaseLocationPermissionRequestCounter()
    suspend fun updateSpeedUnit(unit: SpeedUnit)
    suspend fun updateTemperatureUnit(unit: TemperatureUnit)

    suspend fun updateHourUnit(unit: HourUnit)
}