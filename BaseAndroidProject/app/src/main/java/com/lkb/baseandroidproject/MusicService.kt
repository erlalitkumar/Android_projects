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
    private var lastStation = ""
    private var lastPlayedStationUrl = ""
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
            // Log.d(tag, "onHandle with the msg")
            val url = msg?.data?.getString("url") // your URL here
            if (currentStation.contentEquals(msg?.data?.getString("channel").toString())) {
                //pausePlayBack()
                // stopService()
                stopPlayer()
                currentStation = "NA"
            } else {
                currentStation = msg?.data?.getString("channel").toString()
                lastStation = currentStation
                lastPlayedStationUrl = msg?.data?.getString("url").toString()
                // Log.d(tag, "onHandle with the msg $url")
                releaseMediaPlayer()
                if (url != null) {
                    createMediaPlayer(url)
                    createNotification()
                }
            }


        }
    }

    override fun onCreate() {
        Log.d(tag, "onCreate")
        instance = this
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    private fun createNotification() {
        val notificationIntent = Intent(this@MusicService, MainActivity::class.java)
        notificationIntent.putExtra("station", currentStation)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0, notificationIntent, 0
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(lastStation)
            .setContentText("Now Playing : ...")
            .setSmallIcon(R.drawable.ic_baseline_play)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }


    override fun onBind(intent: Intent?): IBinder? {
        Log.d(tag, "onBind")
        return mBinder
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "onStartCommand")
        //val channelUrl = intent?.getStringExtra("channel") ?: "http://prclive1.listenon.in:9960/;"
        // val currentStation = intent?.getStringExtra("station") ?: "NA"
        //if (!isServiceRunning()) {
        // prepareChannel(startId, channelUrl, currentStation)

        return START_STICKY
    }

    private fun prepareChannel(
        startId: Int,
        channelUrl: String,
        currentStation: String
    ) {
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            val bundle = Bundle()
            bundle.putString("url", channelUrl)
            bundle.putString("channel", currentStation)
            msg.data = bundle
            serviceHandler?.sendMessage(msg)
        }
    }

    fun pausePlayBack() {
        mediaPlayer?.pause()
    }

    fun startPlayer(
        startId: Int,
        channelUrl: String,
        currentStation: String
    ) {
        prepareChannel(startId, channelUrl, currentStation)
    }
//    fun stopService(){
//        //this@MusicService.stopSelf()
//        releaseMediaPlayer()
//        stopForeground(true)
//    }

    fun stopPlayer() {
        mediaPlayer?.stop()
        releaseMediaPlayer()
        stopForeground(true)
    }

    fun startLastPlayedStation() {
        prepareChannel(1, lastPlayedStationUrl, lastStation)
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
        Log.d(tag, "onDestroy")
        super.onDestroy()
        releaseMediaPlayer()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
        releaseMediaPlayer()
    }
}
