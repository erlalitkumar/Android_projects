package com.lkb.baseandroidproject

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
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.lkb.baseandroidproject.MyAdapter.RecyclerViewClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import com.crashlytics.android.Crashlytics
import com.lkb.baseandroidproject.model.Station
import com.lkb.baseandroidproject.model.StationList
import io.fabric.sdk.android.Fabric




class MainActivity : AppCompatActivity(), RecyclerViewClickListener,
    IMainPresenter.View {
    private var presenter: MainPresenter? = null
    private var context: Context? = null
    private var stationList: StationList? = null

    override fun onStationData(data: StationList) {
        val stringJson = Gson().toJson(data)
        Log.d("Station", stringJson)
        writeFile("station.json", stringJson)
    }

    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.StaggeredGridLayoutManager
    private val longText = "                                                              "

    companion object {
        var TAG = "MainActivity"
    }

    override fun onClick(item: Station) {
        setCurrentStation(item.title)
        tvNowPlaying.text = "Now Playing : " + item.title + longText
        var intent = Intent(this@MainActivity, MusicService::class.java)
        intent.putExtra("channel", item.url)
        intent.putExtra("station", item.title)
        startService(intent)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Fabric.with(this, Crashlytics())
        context = this@MainActivity
        presenter = MainPresenter(this)
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

        try {
            var slist = readFile("station.json")
            val mapper2 = jacksonObjectMapper()
            stationList = mapper2.readValue(slist)
            Log.d("json", stationList?.let { it.stationList[0].title })
        } catch (e: FileNotFoundException) {
            val stationSource: InputStream = resources.openRawResource(R.raw.station)
            val mapper2 = jacksonObjectMapper()
            stationList = mapper2.readValue(stationSource)
            Log.d("json", stationList?.let { it.stationList[0].title })
        }

        viewManager = staggeredGridLayoutManager
        stationList?.let {
            viewAdapter = MyAdapter(it)
            viewAdapter.currentPlayingStationPosition = getStationIndex(getCurrentStation(), it)
        }

        viewAdapter.setRecyclerViewClickListener(this)

        (mRecyclerView as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        settingImage.setOnClickListener {
            Toast.makeText(this@MainActivity, "Setting icon clicked", Toast.LENGTH_SHORT).show()
        }
        mHomeIcon.setOnClickListener {
            if(viewAdapter.getCurrentPosition()>=0){
                (mRecyclerView as RecyclerView).smoothScrollToPosition(viewAdapter.getCurrentPosition())
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

    private fun getCurrentStation(): String {
        val pref = getSharedPreferences("radio", Context.MODE_PRIVATE)
        return pref.getString("station", "NA")
    }

    private fun setCurrentStation(station: String) {
        val pref = getSharedPreferences("radio", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("station", station)
        editor.commit()
    }

    private fun getStationIndex(name: String, data: StationList): Int {
        for (i in 0 until data.stationList.size) {
            if (data.stationList[i].title.contentEquals(name))
                return i
        }
        return -1
    }

    private fun writeFile(filename: String, fileContents: String) {
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
            Log.d("File", "Success")
        }
    }

    private fun readFile(filename: String): String {
        var fileInputStream: FileInputStream? = null
        fileInputStream = openFileInput(filename)
        var inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }
}
