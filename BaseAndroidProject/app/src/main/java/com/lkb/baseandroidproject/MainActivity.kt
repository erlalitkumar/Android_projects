package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        val gridView = findViewById<View>(R.id.gridView) as GridView
        gridView.adapter = ImageAdapter(this)
    }
}
