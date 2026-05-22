package com.ez.weather4you.domain.usecase

import com.ez.weather4you.domain.entity.forecast.CurrentWeather
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.entity.forecast.toLocalDateTime
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Clock
import kotlin.time.Instant

@Singleton
class ValidateWeatherForecastUseCase @Inject constructor(private val clock: Clock) {

    operator fun invoke(forecast: WeatherForecast): WeatherForecast = with(forecast) {
        val currentInstant = clock.now()

        val filteredDayForecast = dayForecast.filter {
            val currentDateTime = currentInstant.toLocalDateTime(it.time.timeZone)
            it.time.toLocalDateTime().date >= currentDateTime.date
        }

        val filteredHourForecast = hourForecast
            .filter {
                it.time.toLocalDateTime().truncateToHour() >= currentInstant.toLocalDateTime(it.time.timeZone).truncateToHour()
            }
            .take(24)

        val updatedCurrentWeather = getCurrentWeatherOrDefault(currentInstant)

        return copy(
            currentWeather = updatedCurrentWeather,
            dayForecast = filteredDayForecast,
            hourForecast = filteredHourForecast
        )
    }

    operator fun invoke(forecasts: List<WeatherForecast>) = forecasts.map { invoke(it) }

    private fun WeatherForecast.getCurrentWeatherOrDefault(
        currentInstant: Instant
    ): CurrentWeather {
        val lastUpdatedHour = currentWeather.lastUpdated.toLocalDateTime().truncateToHour()
        val currentHour =
            currentInstant.toLocalDateTime(currentWeather.lastUpdated.timeZone).truncateToHour()

        if (lastUpdatedHour == currentHour) {
            return currentWeather
        }

        return hourForecast
            .find { it.time.toLocalDateTime().truncateToHour() == currentHour }
            ?.let { hour ->
                currentWeather.copy(
                    temperature = hour.temperature,
                    feelsLike = hour.feelsLike,
                    condition = hour.condition
                )
            } ?: currentWeather
    }
}

private fun LocalDateTime.truncateToHour(): LocalDateTime = LocalDateTime(date, LocalTime(hour, 0))
