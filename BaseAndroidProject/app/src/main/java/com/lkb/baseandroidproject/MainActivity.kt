package com.lkb.baseandroidproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        var TAG = "MainActivity"
    }

    private var mMusicService: MusicService? = null
    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "Music service disconnected.")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMusicService = (service as MusicService.LocalBinder).service
            Log.d(TAG, "Music service Connected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doBindService()

        btnChannel.setOnClickListener {
            var intent = Intent(this@MainActivity,MusicService::class.java)
            intent.putExtra("channel","http://prclive1.listenon.in:9960/;")
            startService(intent)
        }

        btnChannel3.setOnClickListener {
            var intent = Intent(this@MainActivity,MusicService::class.java)
            intent.putExtra("channel","https://prclive4.listenon.in/International")
            startService(intent)
        }
    }

    private fun doBindService() {
        startService(Intent(this@MainActivity, MusicService::class.java))
        bindService(
            Intent(
                this@MainActivity, MusicService::class.java
            ), mServiceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun doUnbindService() {
        unbindService(mServiceConnection)
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
    }

}
