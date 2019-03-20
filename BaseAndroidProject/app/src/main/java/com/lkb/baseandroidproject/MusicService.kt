package com.lkb.baseandroidproject

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.lkb.baseandroidproject.MyApplication.Companion.CHANNEL_ID

class MusicService : Service() {

    private var instance: MusicService? = null
    private val tag = "MusicService"
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private fun isServiceRunning(): Boolean {
        return instance != null
    }

    // This is the object that receives interactions from clients.
    private val mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        internal val service: MusicService
            get() = this@MusicService
    }

    // Handler that receives messages from the thread.
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        @SuppressLint("MissingPermission")
        override fun handleMessage(msg: Message?) {
            val url = "http://prclive1.listenon.in:9960/;" // your URL here
            val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(url)
                prepare() // might take long! (for buffering, etc)
                start()
            }
        }
    }

    override fun onCreate() {

        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val notificationIntent = Intent(this@MusicService, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("MyRadio")
            .setContentText("Now Playing...")
            .setSmallIcon(R.drawable.ic_baseline_play)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
        Log.d(tag, "onStartCommand executed")
        if (!isServiceRunning()) {
            Log.d(tag, "onStartCommand executed with job");
            serviceHandler?.obtainMessage()?.also { msg ->
                msg.arg1 = startId
                serviceHandler?.sendMessage(msg)
            }
            instance = this@MusicService
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }
}
