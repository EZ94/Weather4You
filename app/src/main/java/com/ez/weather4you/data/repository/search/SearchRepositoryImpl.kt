package com.ez.weather4you.data.repository.search

import com.ez.weather4you.data.remote.SearchRemoteDataSource
import com.ez.weather4you.domain.entity.forecast.Location
import com.ez.weather4you.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {
    private val _suggestions = MutableStateFlow(listOf<Location>())
    override val suggestions: Flow<List<Location>> = _suggestions.asStateFlow()

    override suspend fun search(input: String) {
        _suggestions.emit(searchRemoteDataSource.search(input))
    }
}
