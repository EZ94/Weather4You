package com.ez.weather4you.data.repository.preferences.di

import android.icu.util.LocaleData
import android.icu.util.ULocale
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object MeasurementSystemModule {
    @Singleton
    @Provides
    fun providesMeasurementSystem() = LocaleData.getMeasurementSystem(ULocale.getDefault())
}