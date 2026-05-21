package com.ez.weather4you.ui.pages.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import com.ez.weather4you.ui.navigation.LocationsForecastPageKey
import com.ez.weather4you.ui.navigation.W4YNavKey
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = OnboardingViewModel.Factory::class)
class OnboardingViewModel @AssistedInject constructor(
    val userPreferencesRepository: UserPreferencesRepository,
    @Assisted val navBackStack: NavBackStack<W4YNavKey>
) : ViewModel() {

    fun onPermissionRequested() {
        viewModelScope.launch {
            userPreferencesRepository.increaseLocationPermissionRequestCounter()
        }
        navBackStack.add(LocationsForecastPageKey)
    }

    fun navigateToWeatherForecastPage() {
        navBackStack.add(LocationsForecastPageKey)
    }

    @AssistedFactory
    interface Factory {
        fun create(navBackStack: NavBackStack<W4YNavKey>): OnboardingViewModel
    }

}
