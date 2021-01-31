package com.parkminji.nine2onetest.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle

class GpsService {
    @SuppressLint("MissingPermission")
    fun getLocation(context: Context, success: (Location) -> Unit){
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1f, object: LocationListener{
            override fun onLocationChanged(location: Location?) {
                location?.let {
                    success(it)
                }
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onProviderDisabled(provider: String?) {
            }
        })
    }


}