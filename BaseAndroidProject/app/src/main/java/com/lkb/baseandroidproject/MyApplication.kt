package com.lkb.baseandroidproject

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApplication : Application() {
    companion object {
        const val CHANNEL_ID = "BabyTracker"
    }

    lateinit var childId: String
    lateinit var role: String
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        var sPref = applicationContext.getSharedPreferences("Tracking", 0) //private mode
        childId = sPref.getString("child_id", "")
        role = if (!childId.isEmpty()) "Parent"
        else "Child"
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel =
                NotificationChannel(
                    CHANNEL_ID, "Baby Tracker channel",
                    NotificationManager.IMPORTANCE_DEFAULT
                )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}