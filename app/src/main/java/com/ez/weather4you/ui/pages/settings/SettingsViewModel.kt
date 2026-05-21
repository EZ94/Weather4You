package com.ez.weather4you.ui.pages.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.entity.UserPreferences
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val preferences: StateFlow<UserPreferences?> = userPreferencesRepository.preferences
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun updateTemperatureUnit(unit: TemperatureUnit) {
        viewModelScope.launch {
            userPreferencesRepository.updateTemperatureUnit(unit)
        }
    }

    fun updateHourUnit(unit: HourUnit) {
        viewModelScope.launch {
            userPreferencesRepository.updateHourUnit(unit)
        }
    }
}
