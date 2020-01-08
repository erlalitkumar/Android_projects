package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
@Inject lateinit var car:Car
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var component = DaggerActivityComponent.create()
        //var car = Car()
        component.inject(this)

        Log.d("CAR","new car $car")
    }
}
