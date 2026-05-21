package com.ez.weather4you.data.repository.search.di

import com.ez.weather4you.data.repository.search.SearchRepositoryImpl
import com.ez.weather4you.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class SearchRepositoryModule {

    @Binds
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}