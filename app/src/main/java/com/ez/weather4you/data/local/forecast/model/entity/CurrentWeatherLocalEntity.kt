package com.ez.weather4you.data.local.forecast.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.domain.entity.forecast.CurrentWeather
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import kotlinx.datetime.TimeZone

@Entity(
    tableName = "CURRENT_WEATHER_LOCAL_ENTITY",
    foreignKeys = [
        ForeignKey(
            entity = LocationLocalEntity::class,
            parentColumns = ["id"],
            childColumns = ["location_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["location_id"])]
)
data class CurrentWeatherLocalEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("last_updated") val lastUpdated: Long,
    @ColumnInfo("last_updated_time_zone") val timeZone: TimeZone,
    @ColumnInfo("temperature_celsius") val temperatureCelsius: Float,
    @ColumnInfo("temperature_fahrenheit") val temperatureFahrenheit: Float,
    @ColumnInfo("feels_like_celsius") val feelsLikeCelsius: Float,
    @ColumnInfo("feels_like_fahrenheit") val feelsLikeFahrenheit: Float,
    val condition: Condition,
    @ColumnInfo("location_id") val locationId: Int
)

fun CurrentWeather.toLocalEntity(locationId: Int) = CurrentWeatherLocalEntity(
    id = "$locationId:$lastUpdated",
    lastUpdated = lastUpdated.timeEpochSeconds,
    timeZone = lastUpdated.timeZone,
    temperatureCelsius = temperature.celsius,
    temperatureFahrenheit = temperature.fahrenheit,
    feelsLikeCelsius = feelsLike.celsius,
    feelsLikeFahrenheit = feelsLike.fahrenheit,
    condition = condition,
    locationId = locationId
)

fun CurrentWeatherLocalEntity.toDomain() = CurrentWeather(
    lastUpdated = LocalizedTime(lastUpdated, timeZone),
    temperature = Temperature(
        celsius = temperatureCelsius,
        fahrenheit = temperatureFahrenheit
    ),
    feelsLike = Temperature(
        celsius = feelsLikeCelsius,
        fahrenheit = feelsLikeFahrenheit
    ),
    condition = condition
)