package com.ez.weather4you.data.remote

import com.ez.weather4you.domain.entity.forecast.Location


interface SearchRemoteDataSource {

    suspend fun search(input: String): List<Location>
}