package com.lkb.baseandroidproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val mJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + mJob)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        uiScope.launch {
            delay(1_000)
            Toast.makeText(this@MainActivity,"From coroutine",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}
