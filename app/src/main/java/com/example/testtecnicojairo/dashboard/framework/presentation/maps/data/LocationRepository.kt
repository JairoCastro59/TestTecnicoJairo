package com.example.testtecnicojairo.dashboard.framework.presentation.maps.data

import android.content.Context
import androidx.annotation.MainThread
import java.util.concurrent.ExecutorService

class LocationRepository private constructor(
    private val myLocationManager: MyLocationManager,
    private val executor: ExecutorService
    ) {

    @MainThread
    fun startLocationUpdates() = myLocationManager.startLocationUpdates()

    /**
     * Un-subscribes from location updates.
     */
    @MainThread
    fun stopLocationUpdates() = myLocationManager.stopLocationUpdates()

    companion object {
        @Volatile private var INSTANCE: LocationRepository? = null

        fun getInstance(context: Context, executor: ExecutorService): LocationRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocationRepository(
                    MyLocationManager.getInstance(context),
                    executor)
                    .also { INSTANCE = it }
            }
        }
    }
}