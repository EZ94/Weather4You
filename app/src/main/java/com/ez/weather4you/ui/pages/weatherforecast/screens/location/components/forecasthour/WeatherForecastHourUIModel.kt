package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ez.weather4you.domain.entity.forecast.HourForecast
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources

data class WeatherForecastHourUIModel(
    val time: String,
    @field:DrawableRes val icon: Int,
    val temperature: String,
    @field:StringRes val weatherDescription: Int
)

fun List<HourForecast>.toUIModel(formatUnitUseCase: FormatUnitUseCase) =
    map { it.toUIModel(formatUnitUseCase) }


private fun HourForecast.toUIModel(formatUnitUseCase: FormatUnitUseCase): WeatherForecastHourUIModel {
    val weatherResourcesHour = WeatherConditionResources.associatedWith(condition)
    return WeatherForecastHourUIModel(
        time = formatUnitUseCase(time),
        icon = weatherResourcesHour.icon,
        temperature = formatUnitUseCase(temperature),
        weatherDescription = weatherResourcesHour.text
    )
}
