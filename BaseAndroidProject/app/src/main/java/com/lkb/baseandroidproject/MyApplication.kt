package com.lkb.baseandroidproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class MyApplication : Application() {
    var currentStationName= "NA"
    companion object {
        const val CHANNEL_ID = "MyRadioPlayer"
    }
    var displaySplash = true

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        resetPref()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel =
                NotificationChannel(
                    CHANNEL_ID, "MyRadio",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    fun resetPref(){
        val pref = getSharedPreferences("radio", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("station", "NA")
        editor.commit()
    }
}