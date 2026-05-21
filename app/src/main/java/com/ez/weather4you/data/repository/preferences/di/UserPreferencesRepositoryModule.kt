package com.ez.weather4you.data.repository.preferences.di

import com.ez.weather4you.data.repository.preferences.UserPreferencesRepositoryImpl
import com.ez.weather4you.domain.repository.UserPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class UserPreferencesRepositoryModule {

    @Binds
    abstract fun bindUserPreferencesRepository(userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl): UserPreferencesRepository
}