package com.ez.weather4you.ui.pages.weatherforecast.screens.location

import com.ez.weather4you.domain.entity.forecast.WeatherForecastState
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.LocationForecastComponentUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.toLocationForecastComponentUIModel

data class LocationsForecastScreenUIModel(
    val locations: List<LocationForecastComponentUIModel>,
    val onFabClick: () -> Unit,
    val onSettingsClick: () -> Unit,
    val onVisibilityChange: (Boolean) -> Unit
)

fun List<WeatherForecastState>.toUIModel(
    formatUnitUseCase: FormatUnitUseCase,
    onFabClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onVisibilityChange: (Boolean) -> Unit
) =
    LocationsForecastScreenUIModel(
        locations = map { it.toLocationForecastComponentUIModel(formatUnitUseCase) },
        onFabClick = onFabClick,
        onSettingsClick = onSettingsClick,
        onVisibilityChange = onVisibilityChange
    )


