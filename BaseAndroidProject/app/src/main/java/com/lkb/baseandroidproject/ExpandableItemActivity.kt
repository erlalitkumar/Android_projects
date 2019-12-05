package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.custom_drop_layout.*

class ExpandableItemActivity : AppCompatActivity() {
    var descVisible: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_drop_layout)
        view.setOnClickListener {
            if (descVisible) {
                descriptionTxt.visibility = View.GONE
                descVisible = false
            } else {
                descriptionTxt.visibility = View.VISIBLE
                descVisible = true
            }
        }
    }
}