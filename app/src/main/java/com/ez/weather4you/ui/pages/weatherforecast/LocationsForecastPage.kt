package com.ez.weather4you.ui.pages.weatherforecast

import androidx.compose.runtime.Composable
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.LocationsForecastScreen
import com.ez.weather4you.ui.pages.weatherforecast.screens.initial.InitialScreen
import com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations.NoLocationsScreen

@Composable
fun LocationsForecastPage(state: LocationsForecastUiState) {
    when (state) {
        is LocationsForecastUiState.Initial -> InitialScreen()
        is LocationsForecastUiState.NoLocations -> NoLocationsScreen(state.model)
        is LocationsForecastUiState.LocationsAvailable -> LocationsForecastScreen(state.model)
    }

}

