package com.ez.weather4you.data.local.preferences.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey

object DataStoreKeys {
    val LOCATION_PERMISSION_REQUEST_COUNTER =
        intPreferencesKey("location_permission_request_counter")
    val TEMPERATURE_UNITS = stringPreferencesKey("temperature_units")

    val HOUR_UNITS = stringPreferencesKey("hour_units")
    val SPEED_UNITS = stringPreferencesKey("speed_units")
}