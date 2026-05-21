package com.ez.weather4you.data.remote

import com.ez.weather4you.data.CURRENT_LOCATION_ID
import com.ez.weather4you.data.remote.model.toDomain
import com.ez.weather4you.data.remote.retrofit.APIService
import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import javax.inject.Inject

class WeatherForecastRemoteDataSourceImpl @Inject constructor(private val service: APIService) :
    WeatherForecastRemoteDataSource {
    override suspend fun getCurrentLocationWeatherForecast(
        coordinates: Coordinates
    ): W4YResult<WeatherForecast> = try {
        val result = service.getForecast("${coordinates.latitude},${coordinates.longitude}")
            .toDomain(CURRENT_LOCATION_ID)
        W4YResult.Success(result)
    } catch (e: Exception) {
        W4YResult.Error(e)
    }


    override suspend fun getSavedLocationWeatherForecast(id: Int): W4YResult<WeatherForecast> = try {
        val result = service.getForecast("id:$id").toDomain(id)
        W4YResult.Success(result)
    } catch (e: Exception) {
        W4YResult.Error(e)
    }


}