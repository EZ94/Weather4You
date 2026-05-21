package com.ez.weather4you.data.local.preferences

import android.icu.util.LocaleData
import android.icu.util.ULocale
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.ez.weather4you.data.local.preferences.datastore.DataStoreKeys
import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.SpeedUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.entity.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesLocalDataSourceImpl @Inject constructor(
    val dataStore: DataStore<Preferences>,
    measurementSystem: LocaleData.MeasurementSystem
) :
    UserPreferencesLocalDataSource {

    private val defaultSpeedUnit =
        if (measurementSystem == LocaleData.MeasurementSystem.US) SpeedUnit.MPH else SpeedUnit.KPH

    private val defaultTemperatureUnit =
        if (measurementSystem == LocaleData.MeasurementSystem.US) TemperatureUnit.FAHRENHEIT else TemperatureUnit.CELSIUS

    private val defaultHourUnit =
        if (measurementSystem == LocaleData.MeasurementSystem.US) HourUnit.H12 else HourUnit.H24

    override val userPreferences: Flow<UserPreferences> = dataStore.data.catch {
        emit(
            emptyPreferences()
        )
    }.map { preferences ->
        UserPreferences(
            locationPermissionRequestCounter = preferences[DataStoreKeys.LOCATION_PERMISSION_REQUEST_COUNTER]
                ?: 0,
            temperatureUnit = preferences[DataStoreKeys.TEMPERATURE_UNITS]?.let {
                TemperatureUnit.valueOf(
                    it
                )
            } ?: defaultTemperatureUnit,
            hourUnit = preferences[DataStoreKeys.HOUR_UNITS]?.let {
                HourUnit.valueOf(it)
            } ?: defaultHourUnit,
            speedUnit = preferences[DataStoreKeys.SPEED_UNITS]?.let { SpeedUnit.valueOf(it) }
                ?: defaultSpeedUnit
        )


    }

    override suspend fun updateSpeedUnit(unit: SpeedUnit) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[DataStoreKeys.SPEED_UNITS] = unit.name
            }
        }
    }

    override suspend fun updateTemperatureUnit(unit: TemperatureUnit) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[DataStoreKeys.TEMPERATURE_UNITS] = unit.name
            }
        }
    }

    override suspend fun updateHourUnit(unit: HourUnit) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[DataStoreKeys.HOUR_UNITS] = unit.name
            }
        }
    }

    override suspend fun increaseLocationPermissionRequestCounter() {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[DataStoreKeys.LOCATION_PERMISSION_REQUEST_COUNTER] =
                    (preferences[DataStoreKeys.LOCATION_PERMISSION_REQUEST_COUNTER] ?: 0) + 1
            }
        }
    }

}