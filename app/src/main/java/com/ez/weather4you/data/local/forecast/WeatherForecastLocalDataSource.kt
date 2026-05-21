package com.ez.weather4you.data.local.forecast

import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface WeatherForecastLocalDataSource {
    suspend fun getSavedLocationsWeatherForecasts(includeCurrent: Boolean): List<WeatherForecast>
    suspend fun upsertWeatherForecast(weatherForecast: WeatherForecast)

    suspend fun getCurrentLocationWeatherForecast(): WeatherForecast?

    suspend fun insertWeatherForecast(weatherForecast: WeatherForecast)
    suspend fun deleteSavedLocation(locationId: Int)
    fun getSavedLocationsWeatherForecastsFlow(includeCurrent: Boolean): Flow<List<WeatherForecast>>
}