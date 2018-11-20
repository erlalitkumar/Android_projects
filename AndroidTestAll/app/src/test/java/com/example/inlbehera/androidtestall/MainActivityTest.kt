package com.example.inlbehera.androidtestall

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner



@RunWith(RobolectricTestRunner::class)
class MainActivityTest{

    @Test
    fun nothing(){
        var activity: MainActivity = Robolectric.setupActivity(MainActivity::class.java)
    }

}