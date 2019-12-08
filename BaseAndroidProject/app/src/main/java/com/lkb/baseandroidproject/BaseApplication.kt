package com.lkb.baseandroidproject

import android.app.Application
import com.lkb.baseandroidproject.di.AppComponent
import com.lkb.baseandroidproject.di.DaggerAppComponent

class BaseApplication:Application() {
    private lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.create()
    }
    fun getApplicationComponent(): AppComponent {
        return component
    }

}