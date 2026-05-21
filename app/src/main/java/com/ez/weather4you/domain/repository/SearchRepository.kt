package com.ez.weather4you.domain.repository

import com.ez.weather4you.domain.entity.forecast.Location
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    val weatherForecasts: Flow<List<WeatherForecast>>

    val suggestions: Flow<List<Location>>
    suspend fun deleteSavedLocation(id: Int)
    suspend fun search(input: String)
    suspend fun addLocation(id: Int)
}