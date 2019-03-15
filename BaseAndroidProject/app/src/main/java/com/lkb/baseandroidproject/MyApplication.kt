package com.lkb.baseandroidproject

import android.app.Application

class MyApplication: Application() {
    lateinit var childId:String
    lateinit var role:String
    override fun onCreate() {
        super.onCreate()
        var sPref = applicationContext.getSharedPreferences("Tracking",0) //private mode
        childId = sPref.getString("child_id","")
        role = if (!childId.isEmpty()) "Parent"
        else "Child"
    }
}