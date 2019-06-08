package com.lkb.baseandroidproject

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.lkb.baseandroidproject.MyAdapter.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import android.os.StrictMode




class MainActivity : AppCompatActivity(), RecyclerViewClickListener {
    //private var mMusicService: MusicService? = null
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.StaggeredGridLayoutManager
    private val longText = "                                                              "

    companion object {
        var TAG = "MainActivity"
    }

    override fun onClick(item: Station) {
        setCurrentStation(item.title)
        tvNowPlaying.text = "Now Playing : "+item.title+longText
        var intent = Intent(this@MainActivity, MusicService::class.java)
        intent.putExtra("channel", item.url)
        intent.putExtra("station", item.title)
        startService(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        val staggeredGridLayoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            1,
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        )
        // mToolbar.setBackgroundColor(Color.TRANSPARENT)
        val file: InputStream = resources.openRawResource(R.raw.station)
        val mapper2 = jacksonObjectMapper()
        val stationList: StationList = mapper2.readValue(file)
        Log.d("json", stationList.stationList[0].title)

        viewManager = staggeredGridLayoutManager
        var myDataset = stationList
        viewAdapter = MyAdapter(myDataset)
        viewAdapter.currentPlayingStationPosition = getStationIndex(getCurrentStation(), myDataset)
        viewAdapter.setRecyclerViewClickListener(this)

        (mRecyclerView as androidx.recyclerview.widget.RecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        settingImage.setOnClickListener {
            Toast.makeText(this@MainActivity, "Setting icon clicked", Toast.LENGTH_SHORT).show()
        }
        mHomeIcon.setOnClickListener { Toast.makeText(this@MainActivity, "icon clicked", Toast.LENGTH_SHORT).show() }
        mFavIcon.setOnClickListener {  Toast.makeText(this@MainActivity, "icon clicked", Toast.LENGTH_SHORT).show()}
        mLibraryIcon.setOnClickListener {  Toast.makeText(this@MainActivity, "icon clicked", Toast.LENGTH_SHORT).show()}
        mRatingIcon.setOnClickListener { Toast.makeText(this@MainActivity, "icon clicked", Toast.LENGTH_SHORT).show() }
        mPlayIcon.setOnClickListener { Toast.makeText(this@MainActivity, "icon clicked", Toast.LENGTH_SHORT).show() }
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

    fun getCurrentStation(): String {
        val pref = getSharedPreferences("radio", Context.MODE_PRIVATE)
        val station = pref.getString("station", "NA")
        return station
    }

    fun setCurrentStation(station: String) {
        val pref = getSharedPreferences("radio", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("station", station)
        editor.commit()
    }

    fun getStationIndex(name: String, data: StationList): Int {
        for (i in 0 until data.stationList.size) {
            if (data.stationList[i].title.contentEquals(name))
                return i
        }
        return -1
    }

    fun checkServiceRunning(): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.example.yourpackagename.YourServiceName" == service.service.className) {
                return true
            }
        }
        return false
    }
}
