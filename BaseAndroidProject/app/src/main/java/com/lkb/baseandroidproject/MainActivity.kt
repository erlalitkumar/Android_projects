package com.lkb.baseandroidproject

import android.os.Bundle
import com.lkb.baseandroidproject.di.presentation.CarComponent
import com.lkb.baseandroidproject.di.presentation.DaggerCarComponent
import com.lkb.baseandroidproject.di.presentation.DieselEngineModule
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject lateinit var car: Car
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var engine = Engine()
//        var wheels = Wheels()
//        var car = Car(engine,wheels)
        var component = DaggerCarComponent
            .builder()
            .horsePower(150)
            .engineCapacity(1400)
            .build()
        //car = component.getCar()
        component.inject(this)
        car?.drive()
    }
}
