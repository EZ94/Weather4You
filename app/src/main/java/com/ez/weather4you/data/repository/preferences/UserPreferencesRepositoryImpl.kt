package com.ez.weather4you.data.repository.preferences

import android.icu.util.LocaleData
import com.ez.weather4you.data.local.preferences.UserPreferencesLocalDataSource
import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.SpeedUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesLocalDataSource: UserPreferencesLocalDataSource
) :
    UserPreferencesRepository {
    override val preferences = userPreferencesLocalDataSource.userPreferences

    override suspend fun updateSpeedUnit(unit: SpeedUnit) {
        userPreferencesLocalDataSource.updateSpeedUnit(unit)
    }

    override suspend fun updateTemperatureUnit(unit: TemperatureUnit) {
        userPreferencesLocalDataSource.updateTemperatureUnit(unit)
    }

    override suspend fun updateHourUnit(unit: HourUnit) {
        userPreferencesLocalDataSource.updateHourUnit(unit)
    }

    override suspend fun increaseLocationPermissionRequestCounter() {
        userPreferencesLocalDataSource.increaseLocationPermissionRequestCounter()
    }

}