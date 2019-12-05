package com.lkb.baseandroidproject.di.presentation

import com.lkb.baseandroidproject.Car
import dagger.Component

@Component
interface CarComponent{
    fun getCar(): Car
}