package com.lkb.baseandroidproject

import android.content.ServiceConnection
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class MediaStateViewModel : ViewModel() {
    lateinit var serviceConnection: ServiceConnection
    var nowPlaying: MutableLiveData<String> = MutableLiveData()
    var adapter = MyAdapter()
    var musicService: MusicService? = null
    lateinit var recyclerView: RecyclerView
    var currentPage = ""
}