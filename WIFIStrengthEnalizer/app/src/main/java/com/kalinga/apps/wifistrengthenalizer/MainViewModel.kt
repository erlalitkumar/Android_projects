package com.kalinga.apps.wifistrengthenalizer

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.net.wifi.WifiManager

class MainViewModel : ViewModel() {
    private val level:MutableLiveData<Int> = MutableLiveData()
    fun getWifiStrength(context: Context): LiveData<Int> {
        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val numberOfLevels = 100
        val wifiInfo = wifiManager.connectionInfo
         level.value = WifiManager.calculateSignalLevel(wifiInfo.rssi, numberOfLevels)
        return level
    }
}