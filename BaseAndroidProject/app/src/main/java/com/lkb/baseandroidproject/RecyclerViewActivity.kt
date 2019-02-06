package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.recycler_main.*

class RecyclerViewActivity : AppCompatActivity() {
    private val tag = "RecyclerViewActivity"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_main)
        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val a = arrayListOf<User>(User("Lalit", "Behera", "Lalit@gmail.com"))
        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        adapter.bindData(a)
        fab.setOnClickListener {
            var intent = Intent(this@RecyclerViewActivity, UserCreateActivity::class.java)
            startActivity(intent)
        }
    }
}