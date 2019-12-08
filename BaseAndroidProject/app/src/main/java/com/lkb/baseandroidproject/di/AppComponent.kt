package com.lkb.baseandroidproject.di

import com.lkb.baseandroidproject.car.Driver
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DriverModule::class])
interface AppComponent {
    fun getDriver(): Driver
}