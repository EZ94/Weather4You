package com.ez.weather4you.data.local.geolocation

import android.annotation.SuppressLint
import android.os.Looper
import com.ez.weather4you.domain.entity.forecast.Coordinates
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Granularity
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import javax.inject.Inject
import kotlin.coroutines.resume

class GeolocationDataSourceImpl @Inject constructor(
    permissionChecker: PermissionChecker,
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val looper: Looper
) : GeolocationDataSource {

    override val hasLocationPermission: Boolean = permissionChecker.hasLocationPermission


    @SuppressLint("MissingPermission")
    override suspend fun getLastKnowLocation(): LocationStatus = withContext(Dispatchers.Main) {
        withTimeoutOrNull(10000) {
            suspendCancellableCoroutine<LocationStatus> { continuation ->
                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult) {
                        val location = locationResult.lastLocation
                        if (location != null && continuation.isActive) {
                            continuation.resume(
                                LocationStatus.Available(
                                    Coordinates(
                                        latitude = location.latitude.toFloat(),
                                        longitude = location.longitude.toFloat()
                                    )
                                )
                            )
                            fusedLocationProviderClient.removeLocationUpdates(this)
                        }
                    }
                }

                if (!hasLocationPermission) {
                    continuation.resume(LocationStatus.PermissionNotGranted)
                    return@suspendCancellableCoroutine
                }

                val cancellationTokenSource = CancellationTokenSource()

                val currentLocationRequest = CurrentLocationRequest.Builder()
                    .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                    .setDurationMillis(5000)
                    .setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                    .build()

                fusedLocationProviderClient.getCurrentLocation(
                    currentLocationRequest,
                    cancellationTokenSource.token,
                ).addOnSuccessListener { location ->
                    if (location != null) {
                        if (continuation.isActive) {
                            continuation.resume(
                                LocationStatus.Available(
                                    Coordinates(
                                        latitude = location.latitude.toFloat(),
                                        longitude = location.longitude.toFloat()
                                    )
                                )
                            )
                        }
                    } else {
                        val locationRequest =
                            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                                .setGranularity(Granularity.GRANULARITY_PERMISSION_LEVEL)
                                .setWaitForAccurateLocation(true)
                                .setMinUpdateIntervalMillis(500)
                                .setMaxUpdates(1)
                                .build()

                        fusedLocationProviderClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            looper
                        ).addOnFailureListener {
                            if (continuation.isActive) {
                                continuation.resume(LocationStatus.NotAvailable)
                            }
                        }
                    }
                }.addOnFailureListener {
                    if (continuation.isActive) {
                        continuation.resume(LocationStatus.NotAvailable)
                    }
                }

                continuation.invokeOnCancellation {
                    cancellationTokenSource.cancel()
                    fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                }
            }
        } ?: LocationStatus.NotAvailable
    }
}

sealed interface LocationStatus {
    data class Available(val coordinates: Coordinates) : LocationStatus
    data object PermissionNotGranted : LocationStatus
    data object NotAvailable : LocationStatus
}
