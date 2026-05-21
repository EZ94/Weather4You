package com.ez.weather4you.domain.usecase

import com.ez.weather4you.domain.entity.HourUnit
import com.ez.weather4you.domain.entity.TemperatureUnit
import com.ez.weather4you.domain.entity.forecast.LocalizedTime
import com.ez.weather4you.domain.entity.forecast.Temperature
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.Padding
import kotlinx.datetime.toLocalDateTime
import javax.inject.Inject
import kotlin.time.Instant

@ViewModelScoped
class FormatUnitUseCase @Inject constructor(private val userPreferencesRepository: UserPreferencesRepository) {

    private val _temperatureUnit: MutableStateFlow<TemperatureUnit?> = MutableStateFlow(null)
    private val _hourUnit: MutableStateFlow<HourUnit?> = MutableStateFlow(null)

    init {
        CoroutineScope(Dispatchers.Default).launch {
            userPreferencesRepository.preferences.collect {
                _temperatureUnit.value = it.temperatureUnit
                _hourUnit.value = it.hourUnit
            }
        }
    }

    operator fun invoke(temperature: Temperature): String =
        (_temperatureUnit.value.let { if (it == TemperatureUnit.CELSIUS) "${temperature.celsius}" else "${temperature.fahrenheit}" }) + "\u00b0"

    operator fun invoke(time: LocalizedTime): String =
        Instant.fromEpochSeconds(time.timeEpochSeconds).toLocalDateTime(time.timeZone)
            .format(if (_hourUnit.value == HourUnit.H24) LocalDateTime.Format {
                hour(Padding.NONE)
            }
            else LocalDateTime.Format {
                amPmHour(Padding.NONE)
                amPmMarker("am", "pm")
            })

    suspend fun update(unit: TemperatureUnit) {
        userPreferencesRepository.updateTemperatureUnit(unit)
    }

    suspend fun update(unit: HourUnit) {
        userPreferencesRepository.updateHourUnit(unit)
    }
}
