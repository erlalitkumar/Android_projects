package com.lkb.baseandroidproject.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.lkb.baseandroidproject.service.CommunicationService
import com.lkb.baseandroidproject.R
import com.lkb.baseandroidproject.model.LocationData

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


    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val sydney = LatLng(0.0, 0.0)
        val marker =  mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        observer = Observer {
            marker.position = LatLng(it.latitude,it.longitude)
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(marker.position))
        }

    }

    private fun doBindService() {
        bindService(Intent(this@MapsActivity, CommunicationService::class.java), mConnection, Context.BIND_AUTO_CREATE)
    }

    private fun doUnbindService() {
        unbindService(mConnection)
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
    }


}
