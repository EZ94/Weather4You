package com.ez.weather4you.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface W4YNavKey : NavKey

@Serializable
data object LocationsForecastPageKey : W4YNavKey

@Serializable
data object SearchPageKey : W4YNavKey

@Serializable
data object SettingsPageKey : W4YNavKey

@Serializable
data object OnboardingPageKey: W4YNavKey