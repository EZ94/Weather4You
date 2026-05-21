package com.ez.weather4you.data.local.forecast.model

import androidx.room.Embedded
import androidx.room.Relation
import com.ez.weather4you.data.local.forecast.model.entity.CurrentWeatherLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.DayForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.HourForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.LocationLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.toDomain
import com.ez.weather4you.data.local.forecast.model.entity.toLocalEntity
import com.ez.weather4you.domain.entity.forecast.WeatherForecast


data class WeatherForecastLocalModel(
    @Embedded val location: LocationLocalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "location_id"
    )
    val currentWeather: CurrentWeatherLocalEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "location_id"
    )
    val dayForecast: List<DayForecastLocalEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "location_id"
    )
    val hourForecast: List<HourForecastLocalEntity>
)

fun WeatherForecast.toLocalModel(timeStamp: Long): WeatherForecastLocalModel {
    val locationEntity = location.toLocalEntity(timeStamp)
    return WeatherForecastLocalModel(
        location = locationEntity,
        currentWeather = currentWeather.toLocalEntity(locationEntity.id),
        dayForecast = dayForecast.toLocalEntity(locationEntity.id),
        hourForecast = hourForecast.toLocalEntity(locationEntity.id)
    )
}

fun WeatherForecastLocalModel.toDomain() = WeatherForecast(
    location = location.toDomain(),
    currentWeather = currentWeather.toDomain(),
    dayForecast = dayForecast.toDomain(),
    hourForecast = hourForecast.toDomain()
)

fun List<WeatherForecastLocalModel>.toDomain(): List<WeatherForecast> = map { it.toDomain() }
