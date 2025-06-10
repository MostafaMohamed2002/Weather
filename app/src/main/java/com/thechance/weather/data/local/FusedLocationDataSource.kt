package com.thechance.weather.data.local

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.thechance.weather.data.repository.location.LocationDataSource
import com.thechance.weather.domain.exception.GpsDisabledException
import com.thechance.weather.domain.exception.LocationNotFoundException
import com.thechance.weather.domain.exception.LocationPermissionException
import com.thechance.weather.domain.model.Location
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FusedLocationDataSource(
    private val context: Context
) : LocationDataSource {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context.applicationContext)

    @SuppressLint("MissingPermission")
    override suspend fun fetchCurrentLocation(): Location {
        if (!hasPermission()) {
            throw LocationPermissionException()
        }
        if (!isGpsEnabled()) {
            throw GpsDisabledException()
        }

        return suspendCancellableCoroutine { continuation ->
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location: android.location.Location? ->
                if (location != null) {
                    continuation.resume(Location(latitude = location.latitude, longitude = location.longitude))
                } else {
                    continuation.resumeWithException(LocationNotFoundException("The location provider returned a null location."))
                }
            }.addOnFailureListener { exception ->
                continuation.resumeWithException(LocationNotFoundException("The underlying location provider failed."))
            }

            continuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
        }
    }

    private fun hasPermission(): Boolean {
        val hasFineLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasCoarseLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        return hasFineLocationPermission || hasCoarseLocationPermission
    }

    private fun isGpsEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}