package com.lkb.baseandroidproject

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if ((application as MyApplication).displaySplash) {
            DelayTask().execute()
        } else {
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    inner class DelayTask : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            Thread.sleep(2000)
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            (application as MyApplication).displaySplash = false
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

    }
}