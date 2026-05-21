package com.ez.weather4you.domain.repository

import com.ez.weather4you.data.local.geolocation.LocationStatus

interface GeolocationRepository {
    suspend fun getCurrentCoordinates(): LocationStatus
    val hasFineLocationPermission: Boolean
}
