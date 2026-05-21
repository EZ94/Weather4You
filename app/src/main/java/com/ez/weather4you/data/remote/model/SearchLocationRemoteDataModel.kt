package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.Location
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchLocationRemoteDataModel(
    val name: String,
    val region: String?,
    val country: String,
    @SerialName("lat") val latitude: Float,
    @SerialName("lon") val longitude: Float,
    val id: Int
)

fun SearchLocationRemoteDataModel.toDomain() = Location(
    name = name,
    region = region,
    country = country,
    coordinates = Coordinates(
        latitude = latitude,
        longitude = longitude
    ),
    id = id
)

fun List<SearchLocationRemoteDataModel>.toDomain() = map { it.toDomain() }