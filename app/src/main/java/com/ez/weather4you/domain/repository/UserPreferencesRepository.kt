package com.ez.weather4you.domain.repository

import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.SpeedUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.entity.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    val preferences: Flow<UserPreferences>
    suspend fun updateSpeedUnit(unit: SpeedUnit)
    suspend fun updateTemperatureUnit(unit: TemperatureUnit)

    suspend fun updateHourUnit(unit: HourUnit)
    suspend fun increaseLocationPermissionRequestCounter()
}