package com.lkb.baseandroidproject

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lkb.baseandroidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val mainActivity: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val usr = User("Lalit", "lalitkbehera@gmail.com")
        mainActivity.user = usr
    }
}
