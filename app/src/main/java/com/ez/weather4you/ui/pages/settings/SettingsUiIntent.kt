package com.ez.weather4you.ui.pages.settings

import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.TemperatureUnit

sealed interface SettingsUiIntent {
    data class UpdateTemperatureUnit(val unit: TemperatureUnit) : SettingsUiIntent
    data class UpdateHourUnit(val unit: HourUnit) : SettingsUiIntent
}
