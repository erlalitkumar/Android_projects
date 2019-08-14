package com.lkb.baseandroidproject

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.crashlytics.android.Crashlytics
import com.lkb.baseandroidproject.model.StationList
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMainPresenter.View {
    private var presenter: MainPresenter? = null
    private var model: MediaStateViewModel? = null
    override fun onStationData(data: StationList) = Unit

    companion object {
        var TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this)[MediaStateViewModel::class.java]
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

        mHomeIcon.setOnClickListener {
            if (model?.adapter!!.getCurrentPosition() >= 0) {
                model?.recyclerView?.smoothScrollToPosition(model?.adapter!!.getCurrentPosition())
            }
        }
        mFavIcon.setOnClickListener {
            //fav icon
        }
        mLibraryIcon.setOnClickListener {
            presenter?.requestStationData()
        }
        mRatingIcon.setOnClickListener {
        }
        mPlayIcon.setOnClickListener {
            Toast.makeText(
                this@MainActivity,
                "icon clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
