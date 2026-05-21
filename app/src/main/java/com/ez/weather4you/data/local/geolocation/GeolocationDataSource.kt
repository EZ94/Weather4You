package com.ez.weather4you.data.local.geolocation

interface GeolocationDataSource {
    suspend fun getLastKnowLocation(): LocationStatus
    val hasLocationPermission: Boolean

}