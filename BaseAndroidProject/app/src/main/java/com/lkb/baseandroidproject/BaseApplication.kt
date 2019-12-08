package com.lkb.baseandroidproject

import android.app.Application
import com.lkb.baseandroidproject.di.CarComponent
import com.lkb.baseandroidproject.di.DaggerCarComponent

class BaseApplication:Application() {
    private lateinit var component: CarComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerCarComponent
            .builder()
            .horsePower(150)
            .engineCapacity(1400)
            .build()
    }
    fun getApplicationComponent(): CarComponent {
        return component
    }

}