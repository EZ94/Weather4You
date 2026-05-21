package com.ez.weather4you.data.local.forecast.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ez.weather4you.data.local.forecast.room.converter.TimeZoneConverter
import com.ez.weather4you.data.local.forecast.model.entity.CurrentWeatherLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.DayForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.HourForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.LocationLocalEntity

@Database(
    entities = [LocationLocalEntity::class, CurrentWeatherLocalEntity::class, HourForecastLocalEntity::class, DayForecastLocalEntity::class],
    version = 1
)
@TypeConverters(TimeZoneConverter::class)
abstract class W4YRoomDatabase : RoomDatabase() {
    abstract fun getW4YDao(): W4YDao
}