package com.lkb.baseandroidproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.lkb.baseandroidproject.Constants.Companion.STATION_JSON_FILE_NAME
import com.lkb.baseandroidproject.model.Station
import com.lkb.baseandroidproject.model.StationList
import kotlinx.android.synthetic.main.home_layout.*
import java.io.*

class HomeFragment : Fragment(), MyAdapter.RecyclerViewClickListener,
    IMainPresenter.View {
    private var presenter: MainPresenter? = null
    private var stationList: StationList? = null
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: androidx.recyclerview.widget.StaggeredGridLayoutManager
    private var mediaStateViewModel: MediaStateViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter(this)
        mediaStateViewModel = (activity?.run {
            ViewModelProviders.of(this)[MediaStateViewModel::class.java]
        } ?: throw Exception("Invalid Activity"))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_layout, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mediaStateViewModel?.recyclerView = mRecyclerView as RecyclerView
        try {
            var sList = readFile(STATION_JSON_FILE_NAME)
            val mapper2 = jacksonObjectMapper()
            stationList = mapper2.readValue(sList)
            Log.d("json", stationList?.let { it.stationList[0].title })
        } catch (e: FileNotFoundException) {
            val stationSource: InputStream = resources.openRawResource(R.raw.station)
            val mapper2 = jacksonObjectMapper()
            stationList = mapper2.readValue(stationSource)
            Log.d("json", stationList?.let { it.stationList[0].title })
        }
        val staggeredGridLayoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(
            1,
            androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
        )
        viewManager = staggeredGridLayoutManager
        stationList?.let {
            viewAdapter = MyAdapter(it)
            viewAdapter.currentPlayingStationPosition = getStationIndex(getCurrentStation(), it)
            mediaStateViewModel?.adapter = viewAdapter
        }

        viewAdapter.setRecyclerViewClickListener(this)

        (mRecyclerView as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        settingImage.setOnClickListener {
            var fm = activity?.supportFragmentManager
            var ft = fm?.beginTransaction()
            ft?.replace(R.id.container, SettingFragment.newInstance(), "setting")
            ft?.addToBackStack("setting")
            ft?.commit()
        }

    }

    override fun onClick(item: Station) {
        setCurrentStation(item.title)
        mediaStateViewModel?.nowPlaying?.value = "Now Playing : " + item.title
        activity?.startService(getMusicServiceIntent(item))
        activity?.bindService(
            getMusicServiceIntent(item),
            mediaStateViewModel?.serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun getMusicServiceIntent(item: Station): Intent {
        var intent = Intent(activity, MusicService::class.java)
        intent.putExtra("channel", item.url)
        intent.putExtra("station", item.title)
        return intent
    }

    override fun onStationData(data: StationList) = Unit

    private fun getCurrentStation(): String {
        val pref = activity?.getSharedPreferences("radio", Context.MODE_PRIVATE)
        return pref?.getString("station", "NA")!!
    }

    private fun setCurrentStation(station: String) {
        val pref = activity?.getSharedPreferences("radio", Context.MODE_PRIVATE)
        val editor = pref?.edit()
        editor?.putString("station", station)
        editor?.commit()
    }

    private fun getStationIndex(name: String, data: StationList): Int {
        for (i in data.stationList.indices) {
            if (data.stationList[i].title.contentEquals(name))
                return i
        }
        return -1
    }

    private fun readFile(filename: String): String {
        var fileInputStream: FileInputStream? = null
        fileInputStream = activity?.openFileInput(filename)
        var inputStreamReader = InputStreamReader(fileInputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val stringBuilder: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = bufferedReader.readLine(); text }() != null) {
            stringBuilder.append(text)
        }
        return stringBuilder.toString()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}