package com.ez.weather4you.domain.usecase

import com.ez.weather4you.data.local.geolocation.LocationStatus
import com.ez.weather4you.data.remote.W4YResult
import com.ez.weather4you.domain.entity.forecast.CurrentLocationWeatherForecastState
import com.ez.weather4you.domain.entity.forecast.WeatherForecastState
import com.ez.weather4you.domain.repository.GeolocationRepository
import com.ez.weather4you.domain.repository.WeatherForecastRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WeatherForecastUseCase @Inject constructor(
    private val geolocationRepository: GeolocationRepository,
    private val validateWeatherForecastUseCase: ValidateWeatherForecastUseCase,
    private val weatherForecastRepository: WeatherForecastRepository
) {


    val weatherForecast: Flow<List<WeatherForecastState>> =
        weatherForecastRepository.savedLocationsWeatherForecasts.map { forecastStates ->
            forecastStates.map { it.copyWithState(validateWeatherForecastUseCase(it.state)) }
        }

    fun getCurrentLocationFlow(): Flow<CurrentLocationWeatherForecastState> = flow {
        val backupForecast = weatherForecastRepository.getLastKnownCurrentLocationWeatherForecast()
        backupForecast?.let {
            emit(
                CurrentLocationWeatherForecastState.Available(
                    WeatherForecastState.Refreshing(it).copyWithState(validateWeatherForecastUseCase(it))
                )
            )
        }
        geolocationRepository.getCurrentCoordinates().let { status ->
            when (status) {
                is LocationStatus.Available ->
                    emit(
                        weatherForecastRepository.getFreshCurrentLocationWeatherForecast(
                            status.coordinates
                        ).let { result ->
                            when (result) {
                                is W4YResult.Success -> CurrentLocationWeatherForecastState.Available(
                                    WeatherForecastState.New(result.value).copyWithState(validateWeatherForecastUseCase(result.value))
                                )

                                is W4YResult.Error -> backupForecast?.let {
                                    CurrentLocationWeatherForecastState.Available(
                                        WeatherForecastState.Error(it).copyWithState(validateWeatherForecastUseCase(it))
                                    )
                                } ?: CurrentLocationWeatherForecastState.Empty(true)
                            }

                        }


                    )

                is LocationStatus.NotAvailable -> emit(
                    CurrentLocationWeatherForecastState.Empty(
                        true
                    )
                )

                is LocationStatus.PermissionNotGranted -> {
                    weatherForecastRepository.deleteCurrentLocation()
                    emit(CurrentLocationWeatherForecastState.Empty(false))
                }
            }
        }

    }

    suspend fun refreshSavedLocations() {
        weatherForecastRepository.refreshSavedLocations()
    }
}
