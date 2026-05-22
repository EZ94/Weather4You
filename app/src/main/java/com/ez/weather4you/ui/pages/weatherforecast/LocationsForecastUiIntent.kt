package com.ez.weather4you.ui.pages.weatherforecast

sealed interface LocationsForecastUiIntent {
    data object OpenSearch : LocationsForecastUiIntent
    data object OpenSettings : LocationsForecastUiIntent
    data class Refresh(val isVisible: Boolean) : LocationsForecastUiIntent
}
