package com.ez.weather4you.data.remote

import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.WeatherForecast

interface WeatherForecastRemoteDataSource {

    suspend fun getCurrentLocationWeatherForecast(
        coordinates: Coordinates
    ): W4YResult<WeatherForecast>

    suspend fun getSavedLocationWeatherForecast(
        id: Int
    ): W4YResult<WeatherForecast>

}


//TODO: make it a separate class in domain

sealed interface W4YResult<T> {
    data class Success<T>(val value: T): W4YResult<T>
    data class Error<T>(val exception: Exception): W4YResult<T>
}