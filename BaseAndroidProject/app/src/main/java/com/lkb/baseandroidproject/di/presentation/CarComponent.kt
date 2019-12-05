package com.lkb.baseandroidproject.di.presentation

import com.lkb.baseandroidproject.Car
import com.lkb.baseandroidproject.MainActivity
import dagger.Component

@Component(modules = [WheelsModule::class])
interface CarComponent{
    fun getCar(): Car
    fun inject(mainActivity: MainActivity)
}