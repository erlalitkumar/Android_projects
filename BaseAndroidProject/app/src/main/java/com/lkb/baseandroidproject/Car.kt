package com.lkb.baseandroidproject

import android.util.Log
import javax.inject.Inject
const val  TAG = "CAR"
class Car @Inject constructor(engine:Engine,wheel:Wheel) {

    fun drive(){
        Log.d(TAG, "Car is Driving")
    }
}