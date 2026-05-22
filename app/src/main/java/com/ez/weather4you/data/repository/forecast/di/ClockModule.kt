package com.ez.weather4you.data.repository.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.time.Clock

@InstallIn(SingletonComponent::class)
@Module
object ClockModule {

    @Provides
    fun providesClock(): Clock = Clock.System

}