package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.create_user_layout.*
import kotlinx.android.synthetic.main.recycler_main.*

class RecyclerViewActivity : AppCompatActivity() {
    private val tag = "RecyclerViewActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_main)
        fab.setOnClickListener {
            var intent = Intent(this@RecyclerViewActivity,UserCreateActivity::class.java)
            startActivity(intent)
        }
    }
}