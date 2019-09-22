package com.lkb.baseandroidproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.lkb.baseandroidproject.model.StationList
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), IMainPresenter.View {

    companion object {
        const val  TAG = "MainActivity"
    }

    private var isBound: Boolean = false
    private var presenter: MainPresenter? = null
    private var mediaStateViewModel: MediaStateViewModel? = null
    private var musicService: MusicService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSetUp()
        applyStatusBarStyle()
        updateNowPlayingText()
        initListeners()
        startFragment(HomeFragment.newInstance())
    }

    override fun onStart() {
        super.onStart()
        startService(getMusicServiceIntent())
        bindService(
            getMusicServiceIntent(),
            mediaStateViewModel?.serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    /**
     * Initial setup
     */
    private fun initSetUp() {
        var serviceConnection = createServiceConnection()
        mediaStateViewModel = ViewModelProviders.of(this)[MediaStateViewModel::class.java]
        mediaStateViewModel?.let { it.serviceConnection = serviceConnection }
        presenter = MainPresenter(this)
    }

    /**
     * This method is used to apply the status bar style.
     */
    private fun applyStatusBarStyle() {
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * Method to update the now playing text.
     */
    private fun updateNowPlayingText() {
        mediaStateViewModel?.nowPlaying?.observe(this, Observer<String> {
            tvNowPlaying.text = it + blankSpaceForMarquee()
        })
    }

    /**
     * This method initializes all the listeners.
     */
    private fun initListeners() {
        mHomeIcon.setOnClickListener { handleHomeButtonClick() }
        mFavIcon.setOnClickListener { /*fav icon*/ }
        mLibraryIcon.setOnClickListener {}
        mRatingIcon.setOnClickListener {}
        mPlayIcon.setOnClickListener { musicService?.let { it.startLastPlayedStation() } }
    }

    /**
     * This method creates the service connection to bind the service.
     */
    private fun createServiceConnection(): ServiceConnection {
        return object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as MusicService.LocalBinder
                musicService = binder.service
                mediaStateViewModel?.let { it.musicService = musicService }
                isBound = true
            }


        }
    }

    /**
     * This method handles the home button click.
     */
    private fun handleHomeButtonClick() {
        mediaStateViewModel?.let {
            if (it.currentPage.contentEquals("HomeFragment") && it.adapter!!.getCurrentPosition() >= 0) {
                it.recyclerView?.let { rv ->
                    rv.smoothScrollToPosition(mediaStateViewModel?.adapter!!.getCurrentPosition())
                }
            }
            if (!it.currentPage.contentEquals("HomeFragment")) {
                startFragment(HomeFragment.newInstance())
            }
        }
    }

    /**
     * This method starts a fragment provided from the local context.
     */
    private fun startFragment(instance: Fragment) {
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container, instance, instance.javaClass.simpleName)
        mediaStateViewModel?.let { it.currentPage = instance.javaClass.simpleName }
        ft.commit()
    }

    /**
     * Method to set the window flag, for the status bar style.
     */
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        when {
            on -> winParams.flags = winParams.flags or bits
            else -> winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    /**
     * This method creates the intent for the music service.
     */
    private fun getMusicServiceIntent(): Intent {
        return Intent(this, MusicService::class.java)
    }

    override fun onStationData(data: StationList) = Unit
/*
    override fun onDestroy() {
        super.onDestroy()
        mediaStateViewModel?.let { vm ->
            vm.musicService?.let {
                it.unbindService(vm.serviceConnection)
            }
        }
    }*/
}
