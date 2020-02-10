package com.lkb.baseandroidproject

import dagger.Component

@Component
interface CarComponent {
    fun getCar():Car
    fun inject(activity:MainActivity)
}