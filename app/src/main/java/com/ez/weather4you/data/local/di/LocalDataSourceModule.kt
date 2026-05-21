package com.ez.weather4you.data.local.di

import com.ez.weather4you.data.local.forecast.WeatherForecastLocalDataSource
import com.ez.weather4you.data.local.forecast.WeatherForecastLocalDataSourceImpl
import com.ez.weather4you.data.local.geolocation.GeolocationDataSource
import com.ez.weather4you.data.local.geolocation.GeolocationDataSourceImpl
import com.ez.weather4you.data.local.geolocation.PermissionChecker
import com.ez.weather4you.data.local.geolocation.PermissionCheckerImpl
import com.ez.weather4you.data.local.preferences.UserPreferencesLocalDataSource
import com.ez.weather4you.data.local.preferences.UserPreferencesLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindSavedLocationsLocalDataSource(
        weatherForecastLocalDataSourceImpl: WeatherForecastLocalDataSourceImpl
    ): WeatherForecastLocalDataSource

    @Binds
    abstract fun bindGeolocationDataSource(geolocationDataSource: GeolocationDataSourceImpl): GeolocationDataSource

    @Binds
    abstract fun bindPermissionChecker(permissionCheckerImpl: PermissionCheckerImpl): PermissionChecker

    @Binds
    abstract fun bindUserPreferencesLocalDataSource(userPreferencesLocalDataSourceImpl: UserPreferencesLocalDataSourceImpl): UserPreferencesLocalDataSource
}
