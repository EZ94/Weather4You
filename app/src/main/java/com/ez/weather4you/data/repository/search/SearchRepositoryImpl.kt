package com.ez.weather4you.data.repository.search

import com.ez.weather4you.data.local.forecast.WeatherForecastLocalDataSource
import com.ez.weather4you.data.remote.W4YResult
import com.ez.weather4you.data.remote.SearchRemoteDataSource
import com.ez.weather4you.data.remote.WeatherForecastRemoteDataSource
import com.ez.weather4you.domain.entity.forecast.Location
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource,
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource,
    private val localDataSource: WeatherForecastLocalDataSource
) : SearchRepository {
    private val _suggestions = MutableStateFlow(listOf<Location>())
    override val suggestions: Flow<List<Location>> = _suggestions.asStateFlow()

    override val weatherForecasts: Flow<List<WeatherForecast>> =
        localDataSource.getSavedLocationsWeatherForecastsFlow(true)


    override suspend fun search(input: String) {
        _suggestions.emit(searchRemoteDataSource.search(input))
    }


    // TODO: Deal with Errors
    override suspend fun addLocation(id: Int) {
        val forecastResult = weatherForecastRemoteDataSource.getSavedLocationWeatherForecast(id)
        if (forecastResult is W4YResult.Success)
            localDataSource.upsertWeatherForecast(forecastResult.value)
    }

    override suspend fun deleteSavedLocation(id: Int) {
        localDataSource.deleteSavedLocation(id)
    }
}