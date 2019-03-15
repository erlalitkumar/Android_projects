package com.lkb.baseandroidproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var mBoundLocationService: CommunicationService? = null
    lateinit var observer:Observer<LocationData>

    private var mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MAP", "Location sercie connected to the MapActivity")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mBoundLocationService = (service as CommunicationService.LocalBinder).service
            Log.d("MAP", "Location sercie connected to the MapActivity")

            mBoundLocationService?.currentLocatoin()?.observe(this@MapsActivity,observer)

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        doBindService()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        observer = Observer {
            val sydney = LatLng(it.latitude, it.longitude)
            mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }


        // Add a marker in Sydney and move the camera


    }

    fun doBindService() {
        bindService(Intent(this@MapsActivity, CommunicationService::class.java), mConnection, Context.BIND_AUTO_CREATE)
    }

    fun doUnbindService() {
        unbindService(mConnection)
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
    }


}
