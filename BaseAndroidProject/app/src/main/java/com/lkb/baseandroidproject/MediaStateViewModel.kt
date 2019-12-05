package com.lkb.baseandroidproject

import android.content.ServiceConnection
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.lkb.baseandroidproject.model.StationList
import com.lkb.baseandroidproject.service.MusicService

class MediaStateViewModel : ViewModel() {
    lateinit var serviceConnection: ServiceConnection
    var isPlaying: MutableLiveData<Boolean> = MutableLiveData()
    var nowPlaying: MutableLiveData<String> = MutableLiveData()
    var adapter = StationViewAdapter(StationList(listOf()))
    var musicService: MusicService? = null
    lateinit var recyclerView: RecyclerView
    var currentPage = ""
}