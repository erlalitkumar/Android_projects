package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity : AppCompatActivity() {
    private var currentActivityName: String = "BaseActivity"
    private var screenTime: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LKB!", "onCreate of $currentActivityName")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LKB!", "onDestroy of $currentActivityName")
    }

    fun setName(name: String) {
        currentActivityName = name
    }

    fun setScreenTime(time: String) {
        screenTime = time
        Log.d("LKB!", "screenTime of  $currentActivityName is $screenTime")
    }
}
