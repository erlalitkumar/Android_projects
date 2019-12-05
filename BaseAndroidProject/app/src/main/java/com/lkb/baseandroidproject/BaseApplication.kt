package com.lkb.baseandroidproject

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.lkb.baseandroidproject.di.application.ApplicationComponent
import com.lkb.baseandroidproject.di.application.DaggerApplicationComponent
import io.fabric.sdk.android.Fabric

class BaseApplication : Application() {
    private var mApplicationComponent: ApplicationComponent? = null
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    companion object {
        const val CHANNEL_ID = "MyRadioPlayer"
    }

    var displaySplash = true

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent.builder().build()
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

    fun getApplicationComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}