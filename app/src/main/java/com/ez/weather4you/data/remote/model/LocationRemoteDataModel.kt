package com.ez.weather4you.data.remote.model

import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.Location
import kotlinx.datetime.TimeZone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationRemoteDataModel(
    val name: String,
    val region: String?,
    val country: String,
    @SerialName("lat") val latitude: Float,
    @SerialName("lon") val longitude: Float,
    @SerialName("tz_id") val timeZoneId: String,
    @SerialName("localtime_epoch") val localTimeEpoch: Long,
    @SerialName("localtime") val localTime: String
)

fun LocationRemoteDataModel.toDomain(id: Int) = Location(
    name = name,
    region = region,
    country = country,
    coordinates = Coordinates(
        latitude = latitude,
        longitude = longitude
    ),
    id = id
)