package com.example.gpstracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.util.Log

class GpsStatusReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null && LocationManager.PROVIDERS_CHANGED_ACTION == intent.action) {
            val locationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

            val message = if (isGpsEnabled) {
                "GPS увімкнено"
            } else {
                "GPS вимкнено"
            }

            Log.i("GPS", message)
        }
    }
}
