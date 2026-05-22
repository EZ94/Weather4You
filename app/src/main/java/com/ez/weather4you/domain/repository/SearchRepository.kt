package com.ez.weather4you.domain.repository

import com.ez.weather4you.domain.entity.forecast.Location
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    val suggestions: Flow<List<Location>>
    suspend fun search(input: String)
}
