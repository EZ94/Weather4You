package com.ez.weather4you.data.local.geolocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface PermissionChecker {
    val hasLocationPermission: Boolean
}

class PermissionCheckerImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : PermissionChecker {
    override val hasLocationPermission: Boolean
        get() = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}
