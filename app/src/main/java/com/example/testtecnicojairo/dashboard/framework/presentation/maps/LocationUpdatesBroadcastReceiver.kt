package com.example.testtecnicojairo.dashboard.framework.presentation.maps

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.LocationAvailability
import com.google.android.gms.location.LocationResult

class LocationUpdatesBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("MOVIEAPP", "onReceive() context:$context, intent:$intent")

        if (intent.action == ACTION_PROCESS_UPDATES) {

            // Checks for location availability changes.
            LocationAvailability.extractLocationAvailability(intent)?.let { locationAvailability ->
                if (!locationAvailability.isLocationAvailable) {
                    Log.d("", "Location services are no longer available!")
                }
            }

            LocationResult.extractResult(intent)?.let { locationResult ->
                /*val locations = locationResult.locations.map { location ->
                    MyLocationEntity(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        foreground = isAppInForeground(context),
                        date = Date(location.time)
                    )
                }
                if (locations.isNotEmpty()) {
                    LocationRepository.getInstance(context, Executors.newSingleThreadExecutor())
                        .addLocations(locations)
                }*/
            }
        }
    }

    private fun isAppInForeground(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses ?: return false

        appProcesses.forEach { appProcess ->
            if (appProcess.importance ==
                ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                appProcess.processName == context.packageName) {
                return true
            }
        }
        return false
    }

    companion object {
        const val ACTION_PROCESS_UPDATES =
            "com.example.testtecnicoJairo.action." +
                    "PROCESS_UPDATES"
    }
}
