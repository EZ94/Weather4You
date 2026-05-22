package com.ez.weather4you.data.repository.geolocation.di

import com.ez.weather4you.data.repository.geolocation.GeolocationRepositoryImpl
import com.ez.weather4you.domain.repository.GeolocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GeolocationRepositoryModule {

    @Binds
    abstract fun bindGeolocationRepository(
        geolocationRepositoryImpl: GeolocationRepositoryImpl
    ): GeolocationRepository
}
