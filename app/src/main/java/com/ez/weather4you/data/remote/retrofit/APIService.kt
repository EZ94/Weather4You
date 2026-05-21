package com.ez.weather4you.data.remote.retrofit

import com.ez.weather4you.data.remote.model.ForecastRemoteDataModel
import com.ez.weather4you.data.remote.model.LocationRemoteDataModel
import com.ez.weather4you.data.remote.model.SearchLocationRemoteDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("q") query: String,
        @Query("days") days: Int = 3
    ): ForecastRemoteDataModel


    @GET("search.json")
    suspend fun search(
        @Query("q") input: String
    ): List<SearchLocationRemoteDataModel>
}