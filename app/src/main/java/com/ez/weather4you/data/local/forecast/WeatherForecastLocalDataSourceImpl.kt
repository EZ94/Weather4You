package com.ez.weather4you.data.local.forecast

import com.ez.weather4you.data.CURRENT_LOCATION_ID
import com.ez.weather4you.data.local.forecast.model.toDomain
import com.ez.weather4you.data.local.forecast.model.toLocalModel
import com.ez.weather4you.data.local.forecast.room.W4YDao
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Clock


@Singleton
class WeatherForecastLocalDataSourceImpl @Inject constructor(
    private val w4YDao: W4YDao,
    private val clock: Clock
) : WeatherForecastLocalDataSource {


    override suspend fun getSavedLocationsWeatherForecasts(includeCurrent: Boolean): List<WeatherForecast> =
        (if (includeCurrent) w4YDao.getWeatherForecastForAllSavedLocations()
        else w4YDao.getWeatherForecastForSavedLocations(CURRENT_LOCATION_ID))
            .map { it.toDomain() }

    override fun getSavedLocationsWeatherForecastsFlow(includeCurrent: Boolean): Flow<List<WeatherForecast>> =
        (if (includeCurrent) w4YDao.getWeatherForecastForAllSavedLocationsFlow()
        else w4YDao.getWeatherForecastForSavedLocationsFlow(withIdDifferentFrom = CURRENT_LOCATION_ID))
            .map { forecasts -> forecasts.map { it.toDomain() } }

    override suspend fun upsertWeatherForecast(weatherForecast: WeatherForecast) {
        w4YDao.upsertWeatherForecast(
            weatherForecast.toLocalModel(
                timeStamp = clock.now().epochSeconds
            )
        )
    }

    override suspend fun getCurrentLocationWeatherForecast(): WeatherForecast? =
        w4YDao.getWeatherForecast(id = CURRENT_LOCATION_ID)?.toDomain()


    override suspend fun insertWeatherForecast(weatherForecast: WeatherForecast) {
        w4YDao.insertWeatherForecast(
            weatherForecast.toLocalModel(
                timeStamp = clock.now().epochSeconds
            )
        )
    }

    override suspend fun deleteSavedLocation(locationId: Int) {
        w4YDao.deleteLocation(locationId)
    }

}
