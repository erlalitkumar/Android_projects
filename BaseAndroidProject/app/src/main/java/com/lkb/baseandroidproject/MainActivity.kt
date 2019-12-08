package com.lkb.baseandroidproject

import android.os.Bundle
import com.lkb.baseandroidproject.car.Car
import com.lkb.baseandroidproject.di.DieselEngineModule
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var car1: Car
    @Inject
    lateinit var car2: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var component =
            (application as BaseApplication).getApplicationComponent().getActivityComponent(
                DieselEngineModule(140)
            )

        component.inject(this)

        car1?.drive()
        car2?.drive()
    }
}
