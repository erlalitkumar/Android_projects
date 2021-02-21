package com.lkb.baseandroidproject

import android.app.Application
import io.realm.Realm

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}