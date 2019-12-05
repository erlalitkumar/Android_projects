package com.lkb.baseandroidproject.view

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.WindowManager
import com.lkb.baseandroidproject.BaseApplication
import com.lkb.baseandroidproject.R
import com.lkb.baseandroidproject.view.base.BaseActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if ((application as BaseApplication).displaySplash) {
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
            (application as BaseApplication).displaySplash = false
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

    }
}