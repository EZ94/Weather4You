package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.CurrentWeather
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import kotlinx.datetime.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class CurrentWeatherRemoteDataModel(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val temperatureCelsius: Float,
    @SerialName("temp_f") val temperatureFahrenheit: Float,
    @SerialName("is_day") val isDay: Int,
    val condition: ConditionRemoteDataModel,
    @SerialName("wind_mph") val windMph: Float,
    @SerialName("wind_kph") val windKph: Float,
    @SerialName("wind_degree") val windDegree: Float,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("pressure_mb") val pressureMB: Float,
    @SerialName("pressure_in") val pressurePSI: Float,
    @SerialName("precip_mm") val precipitationsMm: Float,
    @SerialName("precip_in") val precipitationsIn: Float,
    val humidity: Int,
    val cloud: Float,
    @SerialName("feelslike_c") val feelsLikeCelsius: Float,
    @SerialName("feelslike_f") val feelsLikeFahrenheit: Float,
    @SerialName("windchill_c") val windchillCelsius: Float,
    @SerialName("windchill_f") val windchillFahrenheit: Float,
    @SerialName("heatindex_c") val heatIndexCelsius: Float,
    @SerialName("heatindex_f") val heatIndexFahrenheit: Float,
    @SerialName("dewpoint_c") val dewPointCelsius: Float,
    @SerialName("dewpoint_f") val dewPointFahrenheit: Float,
    @SerialName("vis_km") val visibilityKm: Float,
    @SerialName("vis_miles") val visibilityM: Float,
    val uv: Float,
    @SerialName("gust_mph") val gustMph: Float,
    @SerialName("gust_kph") val gustKph: Float

)

fun CurrentWeatherRemoteDataModel.toDomain(timeZone: TimeZone) = CurrentWeather(
    lastUpdated = LocalizedTime(lastUpdatedEpoch, timeZone),
    temperature = Temperature(
        celsius = temperatureCelsius,
        fahrenheit = temperatureFahrenheit
    ),
    feelsLike = Temperature(
        celsius = feelsLikeCelsius,
        fahrenheit = feelsLikeFahrenheit
    ),
    condition = condition.toDomain(isDay == 1)
)
