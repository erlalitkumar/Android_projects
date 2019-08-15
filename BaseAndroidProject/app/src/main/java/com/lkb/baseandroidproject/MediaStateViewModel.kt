package com.lkb.baseandroidproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class MediaStateViewModel : ViewModel() {
    var nowPlaying: MutableLiveData<String> = MutableLiveData()
    var adapter = MyAdapter()
    lateinit var recyclerView: RecyclerView
}