package com.ez.weather4you.data.repository.forecast.di

import com.ez.weather4you.data.repository.forecast.WeatherForecastRepositoryImpl
import com.ez.weather4you.domain.repository.WeatherForecastRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherForecastRepositoryModule {

    @Binds
    abstract fun bindWeatherDetailsRepository(weatherDetailsRepositoryImpl: WeatherForecastRepositoryImpl): WeatherForecastRepository

}
