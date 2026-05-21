package com.ez.weather4you.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ez.weather4you.domain.repository.GeolocationRepository
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import com.ez.weather4you.ui.navigation.LocationsForecastPageKey
import com.ez.weather4you.ui.navigation.OnboardingPageKey
import com.ez.weather4you.ui.navigation.W4YNavKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlinx.coroutines.flow.take

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userPreferencesRepository: UserPreferencesRepository,
    geolocationRepository: GeolocationRepository
) :
    ViewModel() {


    val firstPageNavigationKey: StateFlow<W4YNavKey?> = userPreferencesRepository.preferences.map {
        if (it.locationPermissionRequestCounter == 0 && !geolocationRepository.hasFineLocationPermission) OnboardingPageKey else LocationsForecastPageKey
    }.take(1) // Only determine the INITIAL page once.
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )
}
