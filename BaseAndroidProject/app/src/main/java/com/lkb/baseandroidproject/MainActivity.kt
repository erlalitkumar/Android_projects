package com.lkb.baseandroidproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHandler = Handler()
        mStartButton.setOnClickListener{
            startProgress();
        }
    }

    private fun startProgress() {
        //New thread to perform background operation
        Thread(Runnable {
            for (i in 0..30) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                //Update the value background thread to UI thread
                mHandler!!.post { mProgressBar.progress = i }
            }
        }).start()
    }
}
