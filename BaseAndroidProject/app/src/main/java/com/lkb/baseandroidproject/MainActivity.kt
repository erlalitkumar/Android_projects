package com.lkb.baseandroidproject

import android.content.ComponentName
import android.content.ServiceConnection
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.crashlytics.android.Crashlytics
import com.lkb.baseandroidproject.model.StationList
import com.lkb.core.logger
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMainPresenter.View {
    private var isBound: Boolean = false
    private var presenter: MainPresenter? = null
    private var model: MediaStateViewModel? = null
    override fun onStationData(data: StationList) = Unit
    private var musicService: MusicService? = null

    companion object {
        var TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var serviceConnection = object: ServiceConnection{
            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as MusicService.LocalBinder
                musicService = binder.service
                isBound = true
            }


        }
        model = ViewModelProviders.of(this)[MediaStateViewModel::class.java]
        model?.let { it.serviceConnection = serviceConnection }
        presenter = MainPresenter(this)
        Fabric.with(this, Crashlytics())
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.container, HomeFragment.newInstance())
        ft.commit()
        model?.nowPlaying?.observe(this, Observer<String> {
            tvNowPlaying.text = it+blankSpaceForMarquee()
        })
        mHomeIcon.setOnClickListener {
            if (model?.adapter!!.getCurrentPosition() >= 0) {
                model?.recyclerView?.smoothScrollToPosition(model?.adapter!!.getCurrentPosition())
            }
        }
        mFavIcon.setOnClickListener {
            //fav icon
        }
        mLibraryIcon.setOnClickListener {
        }
        mRatingIcon.setOnClickListener {
        }
        mPlayIcon.setOnClickListener {
//            Toast.makeText(
//                this@MainActivity,
//                "icon clicked",
//                Toast.LENGTH_SHORT
//            ).show()
            musicService?.let { it.stopService() }
        }
    }


    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        when {
            on -> winParams.flags = winParams.flags or bits
            else -> winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
