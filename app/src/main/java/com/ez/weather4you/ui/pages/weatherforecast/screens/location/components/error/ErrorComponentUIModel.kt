package com.ez.weather4you.ui.pages.weatherforecast.screens.location.components.error

import androidx.annotation.StringRes
import com.ez.weather4you.R
import com.ez.weather4you.domain.entity.forecast.WeatherForecastState

data class ErrorComponentUIModel(
    @field:StringRes val message: Int,
    val type: Type
) {
    enum class Type { ERROR, LOADING }
}

fun WeatherForecastState.toErrorComponentUIModel() = when (this) {
    is WeatherForecastState.New -> null
    is WeatherForecastState.Refreshing -> ErrorComponentUIModel(
        R.string.out_of_date_refreshing,
        ErrorComponentUIModel.Type.LOADING
    )

    is WeatherForecastState.Error -> ErrorComponentUIModel(
        R.string.out_of_date_error,
        ErrorComponentUIModel.Type.ERROR
    )
}