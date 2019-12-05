package com.lkb.baseandroidproject

import android.os.Bundle
import com.lkb.baseandroidproject.di.presentation.CarComponent
import com.lkb.baseandroidproject.di.presentation.DaggerCarComponent

class MainActivity : BaseActivity() {
    private var car: Car? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var engine = Engine()
//        var wheels = Wheels()
//        var car = Car(engine,wheels)
        var component = DaggerCarComponent.create()
        car = component.getCar()
        car?.drive()
    }
}
