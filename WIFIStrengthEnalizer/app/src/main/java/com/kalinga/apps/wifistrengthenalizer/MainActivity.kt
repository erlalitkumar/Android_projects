package com.kalinga.apps.wifistrengthenalizer

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var wifiStrengthView: TextView
    private lateinit var wifiQuality: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wifiStrengthView = findViewById(R.id.wifiStrengthView)
        wifiQuality = findViewById(R.id.signalQuality)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)

        val livdata = viewModel.getWifiStrength(this)
        //livdata.observe(this,{level ->wifiQuality.text = level})

//        wifiStrengthView.text = "Wifi Strength:"
        //wifiQuality.text = livdata.value.toString()
    }
}
