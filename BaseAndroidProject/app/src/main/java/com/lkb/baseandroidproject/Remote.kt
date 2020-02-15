package com.lkb.baseandroidproject

import android.util.Log
import javax.inject.Inject

class Remote @Inject constructor(){
    
    public fun setListener(car:Car){
        Log.d(TAG,"remote set for the car $car")
    }
}