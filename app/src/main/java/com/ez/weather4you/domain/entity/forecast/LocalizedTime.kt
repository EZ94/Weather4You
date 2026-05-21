package com.ez.weather4you.domain.entity.forecast

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant


data class LocalizedTime(
    val timeEpochSeconds: Long,
    val timeZone: TimeZone
)

fun LocalizedTime.toLocalDateTime() =
    Instant.fromEpochSeconds(timeEpochSeconds).toLocalDateTime(timeZone)