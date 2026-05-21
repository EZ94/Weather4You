package com.ez.weather4you.domain.entity.forecast

sealed interface WeatherForecastState {
    val state: WeatherForecast

    fun copyWithState(state: WeatherForecast): WeatherForecastState

    data class Refreshing(override val state: WeatherForecast) : WeatherForecastState {
        override fun copyWithState(state: WeatherForecast): Refreshing = copy(state = state)
    }
    data class Error(override val state: WeatherForecast) : WeatherForecastState {
        override fun copyWithState(state: WeatherForecast): Error = copy(state = state)
    }
    data class New(override val state: WeatherForecast) : WeatherForecastState{
        override fun copyWithState(state: WeatherForecast): New = copy(state = state)
    }
//    data class Empty(
//        val isLocationPermissionGranted: Boolean,
//        override val state: WeatherForecast? = null
//    ) : WeatherForecastState
}

sealed interface CurrentLocationWeatherForecastState {
    data class Available(val state: WeatherForecastState) : CurrentLocationWeatherForecastState
    data class Empty(val isPermissionGranted: Boolean) : CurrentLocationWeatherForecastState
}