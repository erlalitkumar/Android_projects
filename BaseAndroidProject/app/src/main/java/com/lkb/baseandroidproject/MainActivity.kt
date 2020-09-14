package com.lkb.baseandroidproject

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels
        val width = displayMetrics.widthPixels
        Log.d("PIXEL", "height is $height and Width is $width")
        val viewManager = GridLayoutManager(this, 4)
        recyclerView.apply {
            adapter = RecyclerViewAdapter(width-(32+4*8), height)
            layoutManager = viewManager
        }
    }
}
