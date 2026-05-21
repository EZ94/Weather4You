package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.*
import kotlinx.datetime.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastRemoteDataModel(
    val location: LocationRemoteDataModel,
    @SerialName("current") val currentWeather: CurrentWeatherRemoteDataModel,
    val forecast: Forecast
) {
    @Serializable
    data class Forecast(@SerialName("forecastday") val forecastDay: List<ForecastDayRemoteDataModel>)
}

fun ForecastRemoteDataModel.toDomain(id: Int): WeatherForecast =
    WeatherForecast(
        location = location.toDomain(id),
        currentWeather = currentWeather.toDomain(TimeZone.of(location.timeZoneId)),
        dayForecast = forecast.forecastDay.toDomain(TimeZone.of(location.timeZoneId)),
        hourForecast = forecast.forecastDay.flatMap { it.hourForecast.toDomain(TimeZone.of(location.timeZoneId)) }
    )
