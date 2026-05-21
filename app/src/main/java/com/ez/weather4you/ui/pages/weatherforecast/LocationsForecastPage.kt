package com.ez.weather4you.ui.pages.weatherforecast

import androidx.compose.runtime.Composable
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.LocationsForecastScreen
import com.ez.weather4you.ui.pages.weatherforecast.screens.initial.InitialScreen
import com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations.NoLocationsScreen

@Composable
fun LocationsForecastPage(state: LocationsForecastPageState) {
    when (state) {
        is LocationsForecastPageState.Initial -> InitialScreen()
        is LocationsForecastPageState.NoLocations -> NoLocationsScreen(state.model)
        is LocationsForecastPageState.LocationsAvailable -> LocationsForecastScreen(state.model)
    }

}

