package com.ez.weather4you.data.remote.di

import com.ez.weather4you.data.remote.SearchRemoteDataSource
import com.ez.weather4you.data.remote.SearchRemoteDataSourceImpl
import com.ez.weather4you.data.remote.WeatherForecastRemoteDataSourceImpl
import com.ez.weather4you.data.remote.WeatherForecastRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class WeatherForecastRemoteDataSourceModule {

    @Binds
    abstract fun bindWeatherForecastRemoteDataSource(weatherForecastRemoteDataSourceImpl: WeatherForecastRemoteDataSourceImpl): WeatherForecastRemoteDataSource

    @Binds
    abstract fun bindSearchRemoteDataSource(searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl): SearchRemoteDataSource
}