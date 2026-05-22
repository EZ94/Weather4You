package com.ez.weather4you.ui.pages.settings

import com.ez.weather4you.domain.entity.UserPreferences

data class SettingsUiState(
    val preferences: UserPreferences? = null
)
