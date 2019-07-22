package com.example.simplegpsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices

/**
 * This example pulls data from a constants file.
 * In actual practice, apps might dynamically create geofences based on the user's location.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        const val GEOFENCE_KEY = "qwe"
        const val GEOFENCE_RADIUS_IN_METERS = 100f
        const val GEOFENCE_EXPIRATION_IN_MILLISECONDS = 10000L
    }

    lateinit var geofencingClient: GeofencingClient

    private val geofenceList = mutableListOf<Geofence>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        geofencingClient = LocationServices.getGeofencingClient(this)

        val location = Location(59.8944, 30.2642)

        val geofence = Geofence.Builder()
            .setRequestId(GEOFENCE_KEY) // identifier
            .setCircularRegion(
                location.latitude,
                location.longitude,
                GEOFENCE_RADIUS_IN_METERS)
            .setExpirationDuration(GEOFENCE_EXPIRATION_IN_MILLISECONDS)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT)
            .build()
        geofenceList.add(geofence)
    }

    private fun getGeofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofences(geofenceList)
            .build()
    }
}
