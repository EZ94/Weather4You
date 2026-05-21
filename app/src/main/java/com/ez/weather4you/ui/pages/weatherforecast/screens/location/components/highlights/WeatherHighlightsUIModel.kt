package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ez.weather4you.domain.entity.forecast.CurrentWeather
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources

data class WeatherHighlightsUIModel(
    @field:DrawableRes val icon: Int,
    @field:StringRes val weatherDescription: Int,
    val temperature: String,
    val feelsLike: String
)

fun CurrentWeather.toUIModel(formatUnitUseCase: FormatUnitUseCase): WeatherHighlightsUIModel {
    val weatherResources = WeatherConditionResources.associatedWith(condition)
    return WeatherHighlightsUIModel(
        icon = weatherResources.icon,
        weatherDescription = weatherResources.text,
        temperature = formatUnitUseCase(temperature),
        feelsLike = formatUnitUseCase(feelsLike)
    )
}
