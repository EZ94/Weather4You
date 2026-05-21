package com.ez.weather4you.domain.repository

import com.ez.weather4you.data.remote.W4YResult
import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.entity.forecast.WeatherForecastState
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    val savedLocationsWeatherForecasts: Flow<List<WeatherForecastState>>

    suspend fun getFreshCurrentLocationWeatherForecast(coordinates: Coordinates): W4YResult<WeatherForecast>
    suspend fun deleteCurrentLocation()

    suspend fun refreshSavedLocations()
    suspend fun getLastKnownCurrentLocationWeatherForecast(): WeatherForecast?
}