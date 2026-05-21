package com.ez.weather4you.data.local.forecast.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ez.weather4you.data.local.forecast.model.WeatherForecastLocalModel
import com.ez.weather4you.data.local.forecast.model.entity.CurrentWeatherLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.DayForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.HourForecastLocalEntity
import com.ez.weather4you.data.local.forecast.model.entity.LocationLocalEntity
import com.ez.weather4you.data.local.forecast.room.annotations.RestrictedDaoApi
import kotlinx.coroutines.flow.Flow

@Dao
interface W4YDao {

    @Transaction
    @Query("SELECT * FROM LOCATION_LOCAL_ENTITY WHERE id != :withIdDifferentFrom ORDER BY time_stamp ASC")
    suspend fun getWeatherForecastForSavedLocations(withIdDifferentFrom: Int): List<WeatherForecastLocalModel>

    @Transaction
    @Query("SELECT * FROM LOCATION_LOCAL_ENTITY ORDER BY time_stamp ASC")
    suspend fun getWeatherForecastForAllSavedLocations(): List<WeatherForecastLocalModel>

    @Transaction
    @Query("SELECT * FROM LOCATION_LOCAL_ENTITY WHERE id != :withIdDifferentFrom ORDER BY time_stamp ASC")
    fun getWeatherForecastForSavedLocationsFlow(withIdDifferentFrom: Int): Flow<List<WeatherForecastLocalModel>>

    @Transaction
    @Query("SELECT * FROM LOCATION_LOCAL_ENTITY ORDER BY time_stamp ASC")
    fun getWeatherForecastForAllSavedLocationsFlow(): Flow<List<WeatherForecastLocalModel>>

    @Transaction
    @Query("SELECT * FROM LOCATION_LOCAL_ENTITY WHERE id = :id LIMIT 1")
    suspend fun getWeatherForecast(id: Int): WeatherForecastLocalModel?

    @OptIn(RestrictedDaoApi::class)
    @Transaction
    suspend fun upsertWeatherForecast(weatherForecast: WeatherForecastLocalModel) {
        insertSavedLocationIgnoreOnConflict(weatherForecast.location)
        deleteWeatherForecastOfLocation(weatherForecast.location.id)
        insertCurrentWeather(weatherForecast.currentWeather)
        insertHourForecast(weatherForecast.hourForecast)
        insertDayForecast(weatherForecast.dayForecast)
    }

    @OptIn(RestrictedDaoApi::class)
    @Transaction
    suspend fun insertWeatherForecast(weatherForecast: WeatherForecastLocalModel) {
        insertSavedLocationReplaceOnConflict(weatherForecast.location)
        insertCurrentWeather(weatherForecast.currentWeather)
        insertHourForecast(weatherForecast.hourForecast)
        insertDayForecast(weatherForecast.dayForecast)
    }

    @Query("DELETE FROM LOCATION_LOCAL_ENTITY WHERE id = :locationId")
    suspend fun deleteLocation(locationId: Int)

    @RestrictedDaoApi
    @Transaction
    fun deleteWeatherForecastOfLocation(id: Int) {
        deleteCurrentWeatherOfLocation(id)
        deleteHourForecastOfLocation(id)
        deleteDayForecastOfLocation(id)
    }

    @RestrictedDaoApi
    @Query("DELETE FROM CURRENT_WEATHER_LOCAL_ENTITY WHERE location_id = :id")
    fun deleteCurrentWeatherOfLocation(id: Int)

    @RestrictedDaoApi
    @Query("DELETE FROM HOUR_FORECAST_LOCAL_ENTITY WHERE location_id = :id")
    fun deleteHourForecastOfLocation(id: Int)

    @RestrictedDaoApi
    @Query("DELETE FROM DAY_FORECAST_LOCAL_ENTITY WHERE location_id = :id")
    fun deleteDayForecastOfLocation(id: Int)

    @RestrictedDaoApi
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSavedLocationIgnoreOnConflict(location: LocationLocalEntity)

    @RestrictedDaoApi
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedLocationReplaceOnConflict(location: LocationLocalEntity)

    @RestrictedDaoApi
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(location: CurrentWeatherLocalEntity)

    @RestrictedDaoApi
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourForecast(forecast: List<HourForecastLocalEntity>)

    @RestrictedDaoApi
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDayForecast(forecast: List<DayForecastLocalEntity>)
}
