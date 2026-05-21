package com.ez.weather4you.data.remote

import com.ez.weather4you.data.remote.model.toDomain
import com.ez.weather4you.data.remote.retrofit.APIService
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val service: APIService): SearchRemoteDataSource {
    override suspend fun search(input: String) =
        service.search(input).toDomain()
}