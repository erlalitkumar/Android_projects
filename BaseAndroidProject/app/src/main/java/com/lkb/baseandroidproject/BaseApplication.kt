package com.lkb.baseandroidproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric

class BaseApplication : Application() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    companion object {
        const val CHANNEL_ID = "MyRadioPlayer"
    }

    var displaySplash = true

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        createNotificationChannel()
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
}