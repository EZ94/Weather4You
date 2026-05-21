package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.DayForecast
import com.ez.weather4you.domain.entity.forecast.HourForecast
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import kotlinx.datetime.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class ForecastDayRemoteDataModel(
    val date: String,
    @SerialName("date_epoch") val dateEpoch: Long,
    val day: Day,
    @SerialName("astro") val astronomicalDetails: AstronomicalDetails,
    @SerialName("hour") val hourForecast: List<Hour>
){
    @Serializable
    data class Day(
        @SerialName("maxtemp_c") val maxTemperatureCelsius: Float,
        @SerialName("maxtemp_f") val maxTemperatureFahrenheit: Float,
        @SerialName("mintemp_c") val minTemperatureCelsius: Float,
        @SerialName("mintemp_f") val minTemperatureFahrenheit: Float,
        @SerialName("avgtemp_c") val avgTemperatureCelsius: Float,
        @SerialName("avgtemp_f") val avgTemperatureFahrenheit: Float,
        @SerialName("maxwind_mph") val maxWindMph: Float,
        @SerialName("maxwind_kph") val maxWindKph: Float,
        @SerialName("totalprecip_mm") val totalPrecipitationsMm: Float,
        @SerialName("totalprecip_in") val totalPrecipitationsIn: Float,
        @SerialName("avgvis_miles") val avgVisibilityM: Float,
        @SerialName("avgvis_km") val avgVisibilityKm: Float,
        @SerialName("daily_will_it_rain") val willItRain: Int,
        @SerialName("daily_chance_of_rain") val chanceOfRain: Int,
        @SerialName("daily_will_it_snow") val willItSnow: Int,
        @SerialName("daily_chance_of_snow") val chanceOfSnow: Int,
        val condition: ConditionRemoteDataModel
    )

    @Serializable
    data class AstronomicalDetails(
            val sunrise: String,
            val sunset: String,
            val moonrise: String,
            val moonset: String,
            @SerialName("moon_phase") val moonPhase: String,
            @SerialName("moon_illumination") val moonIllumination: Float
            )

    @Serializable
    data class Hour(
        @SerialName("time_epoch") val timeEpoch: Long,
        @SerialName("time") val time: String,
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
        @SerialName("snow_cm") val snowCm: Float,
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
        @SerialName("will_it_rain") val willItRain: Int,
        @SerialName("chance_of_rain") val chanceOfRain: Int,
        @SerialName("will_it_snow") val willItSnow: Int,
        @SerialName("chance_of_snow") val chanceOfSnow: Int,
        @SerialName("vis_km") val visibilityKm: Float,
        @SerialName("vis_miles") val visibilityM: Float,
        val uv: Float,
        @SerialName("gust_mph") val gustMph: Float,
        @SerialName("gust_kph") val gustKph: Float
    )
}

fun ForecastDayRemoteDataModel.toDomain(timeZone: TimeZone) = DayForecast(
    time = LocalizedTime(dateEpoch, timeZone),
    condition = day.condition.toDomain(true),
    maxTemperature = Temperature(
        celsius = day.maxTemperatureCelsius,
        fahrenheit = day.maxTemperatureFahrenheit
    ),
    minTemperature = Temperature(
        celsius = day.minTemperatureCelsius,
        fahrenheit = day.minTemperatureFahrenheit
    )
)

@JvmName("toDayForecastDomain")
fun List<ForecastDayRemoteDataModel>.toDomain(timeZone: TimeZone): List<DayForecast> = map { it.toDomain(timeZone) }

fun ForecastDayRemoteDataModel.Hour.toDomain(timeZone: TimeZone): HourForecast = HourForecast(
    temperature = Temperature(
        celsius = temperatureCelsius,
        fahrenheit = temperatureFahrenheit
    ),
    feelsLike = Temperature(
        celsius = feelsLikeCelsius,
        fahrenheit = feelsLikeFahrenheit
    ),
    condition = condition.toDomain(isDay == 1),
    time = LocalizedTime(timeEpoch, timeZone),
)

@JvmName("toHourForecastDomain")
fun List<ForecastDayRemoteDataModel.Hour>.toDomain(timeZone: TimeZone): List<HourForecast> = map { it.toDomain(timeZone) }

