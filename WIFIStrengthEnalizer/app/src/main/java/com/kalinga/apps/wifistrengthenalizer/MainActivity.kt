package com.kalinga.apps.wifistrengthenalizer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.hardware.*
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var wifiStrengthView: TextView
    private lateinit var wifiQuality: TextView
    lateinit var context: Context
    lateinit var livdata: MutableLiveData<Int>
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // wifiStrengthView = findViewById(R.id.wifiStrengthView)
        wifiQuality = findViewById(R.id.signalQuality)
        context = this

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
        livdata = viewModel.getWifiStrength(context) as MutableLiveData<Int>
        val mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val mSensor: Sensor? = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)

        mSensor?.also { sensor ->
            mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
        val myObserver = Observer<Int> {
            wifiQuality.text = it.toString()
        }
        livdata.observe(this, myObserver)
    }

    override fun onSensorChanged(p0: SensorEvent?) {
        livdata = viewModel.getWifiStrength(context) as MutableLiveData<Int>
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }
}
