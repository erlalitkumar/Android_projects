package com.lkb.baseandroidproject

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import java.io.IOException
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = MyLocationListener()

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),4)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            Log.v("GPS", location.latitude.toString())
            Log.v("GPS", location.longitude.toString())
        }
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
    }

    /*---------- Listener class to get coordinates ------------- */
    private inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(loc: Location) {
            // editLocation.setText("")
            // pb.setVisibility(View.INVISIBLE)
            Toast.makeText(
                baseContext,
                "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                        + loc.longitude, Toast.LENGTH_SHORT
            ).show()
            val longitude = "Longitude: " + loc.longitude
            Log.v("MainActivity2", longitude)
            val latitude = "Latitude: " + loc.latitude
            Log.v("MainActivity2", latitude)

            /*------- To get city name from coordinates -------- */
            var cityName: String? = null
            val gcd = Geocoder(baseContext, Locale.getDefault())
            val addresses: List<Address>
            try {
                addresses = gcd.getFromLocation(
                    loc.latitude,
                    loc.longitude, 1
                )
                if (addresses.isNotEmpty()) {
                    System.out.println(addresses[0].locality)
                    cityName = addresses[0].locality
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            val s = (longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName)
            //editLocation.setText(s)
        }

        override fun onProviderDisabled(provider: String) {}

        override fun onProviderEnabled(provider: String) {}

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }



}
