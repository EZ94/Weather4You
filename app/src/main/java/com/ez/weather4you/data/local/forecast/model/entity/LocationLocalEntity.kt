package com.ez.weather4you.data.local.forecast.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.ez.weather4you.domain.entity.forecast.Location

@Entity(
    tableName = "LOCATION_LOCAL_ENTITY",
    indices = [
        Index(value = ["time_stamp"])
    ]
)
data class LocationLocalEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val region: String?,
    val country: String,
    val latitude: Float,
    val longitude: Float,
    @ColumnInfo("time_stamp") val timeStamp: Long
)

fun Location.toLocalEntity(timeStamp: Long): LocationLocalEntity =
    LocationLocalEntity(
        id = id,
        name = name,
        region = region,
        country = country,
        latitude = coordinates.latitude,
        longitude = coordinates.longitude,
        timeStamp = timeStamp
    )

fun LocationLocalEntity.toDomain() = Location(
    name = name,
    region = region,
    country = country,
    coordinates = Coordinates(
        latitude = latitude,
        longitude = longitude
    ),
    id = id
)
