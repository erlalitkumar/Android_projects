package com.lkb.baseandroidproject

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main2.*

class PlayerActivity:AppCompatActivity(){
    var exoPlayer: ExoPlayer? = null
    var mPlayerCurrentPosition: Long? = 0
    var mPlayerCurrentWindow: Int? = 0
    var mPlayerReady: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    /*
          Combinning ExoPlayer with Activity LifeCycle
       */
    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23 || exoPlayer == null) {
            initializePlayer()
        }
    }

    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        if (exoPlayer != null)
            exoPlayer?.release()
        exoPlayer = null
    }

    // This function is responsible to initialize ExoPlayer.
    private fun initializePlayer() {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl()
            )
            video_view?.player = exoPlayer
            video_view?.player?.playWhenReady = true
        }

        val uri = Uri.parse(getString(R.string.exoplayer_media_source))
        exoPlayer?.prepare(buildMediaSource(uri), false, false)
        video_view?.player?.seekTo(mPlayerCurrentWindow!!, mPlayerCurrentPosition!!)
    }

    // This function is responsible to relase framework resources when not needed.
    private fun releasePlayer() {
        if (exoPlayer != null) {
            mPlayerCurrentPosition = exoPlayer?.currentPosition
            mPlayerCurrentWindow = exoPlayer?.currentWindowIndex
            mPlayerReady = exoPlayer?.playWhenReady
            exoPlayer?.release()
        }
    }
    /*
  * HlsMediaSource Builduing from given URI (video source).
  * */
    private fun buildMediaSource(uri: Uri): MediaSource {
        return HlsMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer")
        )
            .setAllowChunklessPreparation(true)
            .createMediaSource(uri)
    }

    // To have full screen streeming expereince in immersive mode.
    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        video_view.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}