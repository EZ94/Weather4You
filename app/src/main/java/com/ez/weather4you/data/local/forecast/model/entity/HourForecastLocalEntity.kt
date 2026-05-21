package com.ez.weather4you.data.local.forecast.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.domain.entity.forecast.HourForecast
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import kotlinx.datetime.TimeZone

@Entity(
    tableName = "HOUR_FORECAST_LOCAL_ENTITY",
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
data class HourForecastLocalEntity(
    @PrimaryKey val id: String,
    @ColumnInfo("temperature_celsius") val temperatureCelsius: Float,
    @ColumnInfo("temperature_fahrenheit") val temperatureFahrenheit: Float,
    @ColumnInfo("feels_like_celsius") val feelsLikeCelsius: Float,
    @ColumnInfo("feels_like_fahrenheit") val feelsLikeFahrenheit: Float,
    val condition: Condition,
    val time: Long,
    @ColumnInfo("time_zone") val timeZone: TimeZone,
    @ColumnInfo("location_id") val locationId: Int
)

fun HourForecast.toLocalEntity(locationId: Int): HourForecastLocalEntity =
    HourForecastLocalEntity(
        id = "$locationId:$time",
        temperatureCelsius = temperature.celsius,
        temperatureFahrenheit = temperature.fahrenheit,
        feelsLikeCelsius = feelsLike.celsius,
        feelsLikeFahrenheit = feelsLike.fahrenheit,
        condition = condition,
        time = time.timeEpochSeconds,
        timeZone = time.timeZone,
        locationId = locationId
    )

fun HourForecastLocalEntity.toDomain() = HourForecast(
    time = LocalizedTime(time, timeZone),
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

fun List<HourForecastLocalEntity>.toDomain(): List<HourForecast> = map { it.toDomain() }

fun List<HourForecast>.toLocalEntity(locationId: Int): List<HourForecastLocalEntity> =
    map { it.toLocalEntity(locationId) }
