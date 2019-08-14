package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class MediaStateViewModel: ViewModel() {
    var adapter = MyAdapter()
    lateinit var recyclerView:RecyclerView
}