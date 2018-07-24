package com.kalinga.apps.wifistrengthenalizer

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.net.wifi.WifiManager
import android.net.wifi.WifiInfo
import android.content.Context.WIFI_SERVICE



class MainActivity : AppCompatActivity() {
    lateinit var wifiStrengthView:TextView
    lateinit var wifiQuality:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wifiStrengthView  = findViewById(R.id.wifiStrengthView)
        wifiQuality = findViewById(R.id.signalQuality)

        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val numberOfLevels = 5
        val wifiInfo = wifiManager.connectionInfo
        val level = WifiManager.calculateSignalLevel(wifiInfo.rssi, numberOfLevels)
        wifiStrengthView.setText(""+wifiInfo.rssi)
        wifiQuality.setText(""+level)
    }
}
