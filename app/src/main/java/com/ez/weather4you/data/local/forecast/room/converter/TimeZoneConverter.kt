package com.ez.weather4you.data.local.forecast.room.converter

import androidx.room.TypeConverter
import kotlinx.datetime.TimeZone

class TimeZoneConverter {

    @TypeConverter
    fun fromTimeZone(value: TimeZone): String {
        return value.id
    }

    @TypeConverter
    fun toTimeZone(value: String): TimeZone {
        return TimeZone.of(value)
    }
}