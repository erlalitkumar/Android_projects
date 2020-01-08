package com.lkb.baseandroidproject

import androidx.appcompat.app.AppCompatActivity
import dagger.Component


@Component
interface ActivityComponent {
    fun inject(activity:MainActivity)
}