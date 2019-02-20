package com.lkb.baseandroidproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val task = MyAsyncTask()
        task.execute()
    }

    override fun onStart() {
        super.onStart()
        Log.w("MainActivity","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.w("MainActivity","onResume")
    }

    inner class MyAsyncTask : AsyncTask<Void, Void, String>() {
        override fun doInBackground(vararg params: Void?): String {
            var count = 0;
            while (true) {
                textView.text = "From AsyncTask" + count++
                Log.w("MainActivity","From AsyncTask" + count++)
            }
            return "hello"
        }
    }
}
