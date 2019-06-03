package com.lkb.baseandroidproject

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.*
import androidx.core.app.NotificationCompat
import android.util.Log
import com.lkb.baseandroidproject.MyApplication.Companion.CHANNEL_ID


class MusicService : Service(), MediaPlayer.OnPreparedListener {
    private var currentStation = "NA"
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
        Log.d(tag, "Track info is : ${mp?.trackInfo.toString()}")
    }

    private var instance: MusicService? = null
    private val tag = "MusicService"
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null
    private var mediaPlayer: MediaPlayer? = null

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
            Log.d(tag, "onHandle with the msg")
            val url = msg?.data?.getString("url") // your URL here
            if (currentStation.contentEquals(msg?.data?.getString("channel").toString())) {
                pausePlayBack()
                currentStation = "NA"
            } else {
                currentStation = msg?.data?.getString("channel").toString()
                Log.d(tag, "onHandle with the msg $url")
                releaseMediaPlayer()
                if (url != null) {
                    createMediaPlayer(url)
                }
            }


        }
    }

    override fun onCreate() {
        val notificationIntent = Intent(this@MusicService, MainActivity::class.java)
        notificationIntent.putExtra("station", currentStation)
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
        val channelUrl = intent?.getStringExtra("channel") ?: "http://prclive1.listenon.in:9960/;"
        val currentStation = intent?.getStringExtra("station") ?: "NA"
        Log.d(tag, "onStartCommand executed")
        //if (!isServiceRunning()) {
        Log.d(tag, "onStartCommand executed with job");
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            val bundle = Bundle()
            bundle.putString("url", channelUrl)
            bundle.putString("channel", currentStation)
            msg.data = bundle
            serviceHandler?.sendMessage(msg)
        }

        return START_STICKY
    }

    fun refreshPlayBack() {

    }

    fun pausePlayBack() {
        mediaPlayer?.pause()
    }

    fun resumePlayBack() {
        mediaPlayer?.start()
    }

    fun createMediaPlayer(url: String) {
        mediaPlayer = MediaPlayer().apply {
            val attributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                    .build()
            setAudioAttributes(attributes)
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener(this@MusicService)
        }
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        releaseMediaPlayer()
    }
}
