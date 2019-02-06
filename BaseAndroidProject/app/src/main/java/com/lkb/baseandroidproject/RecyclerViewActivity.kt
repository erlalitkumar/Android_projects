package com.lkb.baseandroidproject

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_main.*

class RecyclerViewActivity : AppCompatActivity() {
    private val tag = "RecyclerViewActivity"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var db:AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_main)
        recyclerView = findViewById(R.id.my_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //val a = arrayListOf<User>(User("Lalit", "Behera", "Lalit@gmail.com"))
        db= Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "production"
        ).allowMainThreadQueries().build()

        adapter = RecyclerViewAdapter()
        recyclerView.adapter = adapter
        fab.setOnClickListener {
            var intent = Intent(this@RecyclerViewActivity, UserCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val users = db.userDao().getAllUser()
        adapter.bindData(users)
    }
}