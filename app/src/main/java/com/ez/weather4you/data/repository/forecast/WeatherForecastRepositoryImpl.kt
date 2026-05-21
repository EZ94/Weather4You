package com.ez.weather4you.data.repository.forecast

import com.ez.weather4you.data.CURRENT_LOCATION_ID
import com.ez.weather4you.data.local.forecast.WeatherForecastLocalDataSource
import com.ez.weather4you.data.remote.W4YResult
import com.ez.weather4you.data.remote.WeatherForecastRemoteDataSource
import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.entity.forecast.WeatherForecastState
import com.ez.weather4you.domain.repository.WeatherForecastRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Instant

class WeatherForecastRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherForecastLocalDataSource,
    private val remoteDataSource: WeatherForecastRemoteDataSource,
    private val clock: Clock
) : WeatherForecastRepository {
    private val errorLocations = mutableListOf<Int>()


    override val savedLocationsWeatherForecasts: Flow<List<WeatherForecastState>> =
        localDataSource.getSavedLocationsWeatherForecastsFlow(false).map { forecasts ->
            forecasts.map {
                if (it.isStale() && !errorLocations.contains(it.location.id)) WeatherForecastState.Refreshing(
                    it
                )
                else if (it.isStale() && errorLocations.contains(it.location.id)) WeatherForecastState.Error(
                    it
                )
                else WeatherForecastState.New(it)
            }

        }


    override suspend fun getFreshCurrentLocationWeatherForecast(coordinates: Coordinates): W4YResult<WeatherForecast> =
        remoteDataSource.getCurrentLocationWeatherForecast(coordinates).also {
            if (it is W4YResult.Success) localDataSource.insertWeatherForecast(it.value)
        }

    override suspend fun getLastKnownCurrentLocationWeatherForecast(): WeatherForecast? =
        localDataSource.getCurrentLocationWeatherForecast()

    override suspend fun refreshSavedLocations() {
        errorLocations.clear()
        val savedForecast = localDataSource.getSavedLocationsWeatherForecasts(false)
        coroutineScope {
            val forecastResults = savedForecast.map {
                async(Dispatchers.IO) {
                    remoteDataSource.getSavedLocationWeatherForecast(it.location.id)
                }
            }.awaitAll()
            forecastResults.onEachIndexed { index, result ->
                when (result) {
                    is W4YResult.Success -> {
                        localDataSource.upsertWeatherForecast(result.value)
                    }

                    is W4YResult.Error -> errorLocations.add(savedForecast[index].location.id)
                }
            }
        }

    }


    override suspend fun deleteCurrentLocation() {
        localDataSource.deleteSavedLocation(locationId = CURRENT_LOCATION_ID)
    }

    private fun WeatherForecast.isStale(): Boolean {
        val lastUpdated = currentWeather.lastUpdated.timeEpochSeconds
        return clock.now() > Instant.fromEpochSeconds(lastUpdated).plus(15.minutes)
    }
}