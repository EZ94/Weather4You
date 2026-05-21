package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.ColorScheme
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.entity.forecast.WeatherForecastState
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error.ErrorComponentUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error.toErrorComponentUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour.WeatherForecastHourUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.forecasthour.toUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights.WeatherHighlightsUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.highlights.toUIModel
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources

data class LocationForecastComponentUIModel(
    val location: String,
    val error: ErrorComponentUIModel? = null,
    @field:DrawableRes val backgroundImage: Int,
    val colorScheme: ColorScheme,
    val highlights: WeatherHighlightsUIModel? = null,
    val forecastHour: List<WeatherForecastHourUIModel>? = null
)


fun WeatherForecastState.toLocationForecastComponentUIModel(formatUnitUseCase: FormatUnitUseCase) =
    state.toLocationForecastComponentUIModel(formatUnitUseCase).copy(
        error = toErrorComponentUIModel()
    )

private fun WeatherForecast.toLocationForecastComponentUIModel(
    formatUnitUseCase: FormatUnitUseCase
): LocationForecastComponentUIModel {
    val weatherResources = WeatherConditionResources.associatedWith(currentWeather.condition)


    return LocationForecastComponentUIModel(
        location = location.name,
        backgroundImage = weatherResources.backgroundImage,
        colorScheme = weatherResources.colorScheme,
        highlights = currentWeather.toUIModel(formatUnitUseCase),
        forecastHour = hourForecast.toUIModel(formatUnitUseCase)
    )

}

