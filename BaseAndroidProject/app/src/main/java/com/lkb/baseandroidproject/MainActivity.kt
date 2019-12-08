package com.lkb.baseandroidproject

import android.os.Bundle
import com.lkb.baseandroidproject.car.Car
import com.lkb.baseandroidproject.di.DaggerActivityComponent
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var car1: Car
    @Inject
    lateinit var car2: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var component = DaggerActivityComponent
            .builder()
            .horsePower(150)
            .engineCapacity(1400)
            .appComponent((application as BaseApplication).getApplicationComponent())
            .build()

        component.inject(this)

        car1?.drive()
        car2?.drive()
    }
}
