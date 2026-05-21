package com.ez.weather4you.ui.pages.search.components.locations

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ez.weather4you.domain.entity.forecast.WeatherForecast
import com.ez.weather4you.domain.entity.forecast.isCurrent
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.ui.pages.search.components.search.SearchBoxUIModel
import com.ez.weather4you.ui.pages.weatherforecast.resources.WeatherConditionResources

data class LocationComponentUIModel(
    val currentLocation: LocationUIModel?,
    val savedLocations: List<LocationUIModel>,
    val onSwipe: (Int) -> Unit
) {
    data class LocationUIModel(
        val displayName: String,
        val key: Int,
        val temperature: String,
        @field:DrawableRes val icon: Int,
        @field:StringRes val iconDescription: Int
    )

    companion object {
        fun getDefault() = LocationComponentUIModel(null, emptyList(), {})
    }
}

fun List<WeatherForecast>.toLocationComponentUIModel(
    formatUnitUseCase: FormatUnitUseCase,
    filterLocationName: (String) -> Boolean,
    onSwipe: (Int) -> Unit
) =
    with(filter { filterLocationName(it.location.name) }) {
        LocationComponentUIModel(
            savedLocations = filter { !it.location.isCurrent() }.toSavedLocationsUIModel(
                formatUnitUseCase
            ),
            currentLocation = firstOrNull { it.location.isCurrent() }?.toSavedLocationUIModel(
                formatUnitUseCase
            ),
            onSwipe = onSwipe
        )
    }


private fun WeatherForecast.toSavedLocationUIModel(formatUnitUseCase: FormatUnitUseCase) =
    LocationComponentUIModel.LocationUIModel(
        displayName = "${location.name}, ${location.country}",
        key = location.id,
        temperature = formatUnitUseCase(currentWeather.temperature),
        icon = WeatherConditionResources.associatedWith(currentWeather.condition).icon,
        iconDescription = WeatherConditionResources.associatedWith(currentWeather.condition).text
    )

private fun List<WeatherForecast>.toSavedLocationsUIModel(formatUnitUseCase: FormatUnitUseCase) =
    map { it.toSavedLocationUIModel(formatUnitUseCase) }