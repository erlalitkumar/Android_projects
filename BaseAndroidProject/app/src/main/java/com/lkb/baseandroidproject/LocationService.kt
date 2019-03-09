package com.lkb.baseandroidproject

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.*
import android.util.Log
import android.widget.Toast

class LocationService : Service() {
    private var instance: LocationService? = null
    private val tag = "LocationService"
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private fun isServiceRunning(): Boolean {
        return instance != null
    }

    // Handler that receives messages from the thread.
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        @SuppressLint("MissingPermission")
        override fun handleMessage(msg: Message?) {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val locationListener = MyLocationListener()
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
        }
    }

    override fun onCreate() {
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "onStartCommand executed");

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        if (!isServiceRunning()) {
            Log.d(tag, "onStartCommand executed with job");
            serviceHandler?.obtainMessage()?.also { msg ->
                msg.arg1 = startId
                serviceHandler?.sendMessage(msg)
            }
            instance = this@LocationService
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    /*---------- Listener class to get coordinates ------------- */
    private inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(loc: Location) {
            val longitude = "Longitude: " + loc.longitude
            Log.v(tag, longitude)
            val latitude = "Latitude: " + loc.latitude
            Log.v(tag, latitude)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }

    override fun onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
        instance = null
    }
}