package com.ez.weather4you.data.repository.forecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlin.time.Clock

@InstallIn(ViewModelComponent::class)
@Module
object ClockModule {

    @Provides
    fun providesClock(): Clock = Clock.System

}