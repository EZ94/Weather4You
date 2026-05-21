package com.ez.weather4you.domain.entity.forecast

data class WeatherForecast(
    val location: Location,
    val currentWeather: CurrentWeather,
    val dayForecast: List<DayForecast>,
    val hourForecast: List<HourForecast>
)
