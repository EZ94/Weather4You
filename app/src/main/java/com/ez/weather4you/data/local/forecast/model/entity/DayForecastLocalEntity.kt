package com.ez.weather4you.data.local.forecast.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ez.weather4you.domain.entity.forecast.Condition
import com.ez.weather4you.domain.entity.forecast.DayForecast
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import kotlinx.datetime.TimeZone

@Entity(
    tableName = "DAY_FORECAST_LOCAL_ENTITY",
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
data class DayForecastLocalEntity(
    @PrimaryKey val id: String,
    val time: Long,
    @ColumnInfo("time_zone") val timeZone: TimeZone,
    val condition: Condition,
    @ColumnInfo("max_temperature_celsius") val maxTemperatureCelsius: Float,
    @ColumnInfo("max_temperature_fahrenheit") val maxTemperatureFahrenheit: Float,
    @ColumnInfo("min_temperature_celsius") val minTemperatureCelsius: Float,
    @ColumnInfo("min_temperature_fahrenheit") val minTemperatureFahrenheit: Float,
    @ColumnInfo("location_id") val locationId: Int
)

fun DayForecast.toLocalEntity(locationId: Int): DayForecastLocalEntity =
    DayForecastLocalEntity(
        id = "$locationId:$time",
        time = time.timeEpochSeconds,
        timeZone = time.timeZone,
        condition = condition,
        maxTemperatureCelsius = maxTemperature.celsius,
        maxTemperatureFahrenheit = maxTemperature.fahrenheit,
        minTemperatureCelsius = minTemperature.celsius,
        minTemperatureFahrenheit = minTemperature.fahrenheit,
        locationId = locationId
    )

fun DayForecastLocalEntity.toDomain() = DayForecast(
    time = LocalizedTime(time, timeZone),
    condition = condition,
    maxTemperature = Temperature(
        celsius = maxTemperatureCelsius,
        fahrenheit = maxTemperatureFahrenheit
    ),
    minTemperature = Temperature(
        celsius = minTemperatureCelsius,
        fahrenheit = minTemperatureFahrenheit
    )
)

fun List<DayForecastLocalEntity>.toDomain(): List<DayForecast> = map { it.toDomain() }

fun List<DayForecast>.toLocalEntity(locationId: Int): List<DayForecastLocalEntity> =
    map { it.toLocalEntity(locationId) }
