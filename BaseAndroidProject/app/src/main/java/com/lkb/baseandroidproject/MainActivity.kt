package com.lkb.baseandroidproject

import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val url = "http://prclive1.listenon.in:9960/;" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }
    }
}
