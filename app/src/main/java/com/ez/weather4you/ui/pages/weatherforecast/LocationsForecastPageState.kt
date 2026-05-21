package com.ez.weather4you.ui.pages.weatherforecast

import com.ez.weather4you.ui.pages.weatherforecast.screens.location.LocationsForecastScreenUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations.NoLocationsScreenUIModel

sealed interface LocationsForecastPageState {
    object Initial : LocationsForecastPageState
    data class NoLocations(val model: NoLocationsScreenUIModel) :
        LocationsForecastPageState

    data class LocationsAvailable(val model: LocationsForecastScreenUIModel) :
        LocationsForecastPageState
}