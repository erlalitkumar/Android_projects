package com.lkb.baseandroidproject

import android.content.Context.ACTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.app.ActivityManager
import android.content.Context
import android.provider.SyncStateContract
import android.util.Log


object Util {
    private val LOG_TAG = Util::class.java.name

    fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val services = activityManager.getRunningServices(Integer.MAX_VALUE)

        for (runningServiceInfo in services) {
           // Log.d(SyncStateContract.Constants.TAG, String.format("Service:%s", runningServiceInfo.service.className))
            if (runningServiceInfo.service.className.equals(serviceClass.name)) {
                return true
            }
        }
        return false
    }
}
