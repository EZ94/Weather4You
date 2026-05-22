package com.ez.weather4you.ui.pages.weatherforecast

import com.ez.weather4you.ui.pages.weatherforecast.screens.location.LocationsForecastScreenUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations.NoLocationsScreenUIModel

sealed interface LocationsForecastUiState {
    data object Initial : LocationsForecastUiState
    data class NoLocations(val model: NoLocationsScreenUIModel) :
        LocationsForecastUiState

    data class LocationsAvailable(val model: LocationsForecastScreenUIModel) :
        LocationsForecastUiState
}
