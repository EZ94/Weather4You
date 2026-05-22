package com.ez.weather4you.data.repository.geolocation

import com.ez.weather4you.domain.repository.GeolocationRepository
import com.ez.weather4you.data.local.geolocation.GeolocationDataSource
import com.ez.weather4you.data.local.geolocation.LocationStatus
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeolocationRepositoryImpl @Inject constructor(
    private val geolocationDataSource: GeolocationDataSource
) : GeolocationRepository {

    override val hasFineLocationPermission: Boolean = geolocationDataSource.hasLocationPermission

    override suspend fun getCurrentCoordinates(): LocationStatus {
        return geolocationDataSource.getLastKnowLocation()
    }
}
