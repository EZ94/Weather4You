package com.ez.weather4you.ui.pages.weatherforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavBackStack
import com.ez.weather4you.R
import com.ez.weather4you.domain.entity.forecast.CurrentLocationWeatherForecastState
import com.ez.weather4you.domain.usecase.FormatUnitUseCase
import com.ez.weather4you.domain.usecase.WeatherForecastUseCase
import com.ez.weather4you.ui.navigation.SearchPageKey
import com.ez.weather4you.ui.navigation.SettingsPageKey
import com.ez.weather4you.ui.navigation.W4YNavKey
import com.ez.weather4you.ui.pages.weatherforecast.screens.location.toUIModel
import com.ez.weather4you.ui.pages.weatherforecast.screens.nolocations.NoLocationsScreenUIModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = LocationsForecastViewModel.Factory::class)
class LocationsForecastViewModel @AssistedInject constructor(
    private val weatherForecastUseCase: WeatherForecastUseCase,
    private val formatUnitUseCase: FormatUnitUseCase,
    @Assisted private val navBackStack: NavBackStack<W4YNavKey>
) :
    ViewModel() {

    private val _currentLocation = weatherForecastUseCase.getCurrentLocationFlow()

    private val _savedLocations =
        weatherForecastUseCase.weatherForecast

    private val _refresh: MutableSharedFlow<Unit> = MutableSharedFlow(replay = 1)

    init {
        viewModelScope.launch {
            weatherForecastUseCase.refreshSavedLocations()
        }
        viewModelScope.launch {
            _refresh.emit(Unit)
        }
    }

    val state: StateFlow<LocationsForecastPageState> =
        combine(_currentLocation, _savedLocations, _refresh) { currentLocation, savedLocations, _ ->
            if (currentLocation is CurrentLocationWeatherForecastState.Empty && savedLocations.isEmpty()) {
                val message =
                    if (currentLocation.isPermissionGranted) R.string.no_locations_permission_granted
                    else R.string.no_locations_permission_not_granted
                LocationsForecastPageState.NoLocations(
                    NoLocationsScreenUIModel(
                        message = message,
                        onClick = { openSearchPage() }
                    )
                )
            } else LocationsForecastPageState.LocationsAvailable(
                model =
                    ((if (currentLocation is CurrentLocationWeatherForecastState.Available)
                        listOf(currentLocation.state)
                    else listOf()) + savedLocations).toUIModel(
                        formatUnitUseCase = formatUnitUseCase,
                        onFabClick = { openSearchPage() },
                        onSettingsClick = { openSettingsPage() },
                        onVisibilityChange = { refresh(it) }
                    )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = LocationsForecastPageState.Initial
        )

    fun openSearchPage() {
        navBackStack.add(SearchPageKey)
    }

    fun openSettingsPage() {
        navBackStack.add(SettingsPageKey)
    }

    fun refresh(isVisible: Boolean) {
        if (isVisible) {
            viewModelScope.launch {
                _refresh.emit(Unit)
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(navBackStack: NavBackStack<W4YNavKey>): LocationsForecastViewModel
    }
}
