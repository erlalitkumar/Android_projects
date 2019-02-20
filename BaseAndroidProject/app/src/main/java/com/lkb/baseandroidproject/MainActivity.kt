package com.lkb.baseandroidproject

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var textView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        val task = MyAsyncTask()
        task.execute()
    }

    inner class MyAsyncTask:AsyncTask<Void,Void,String>() {
        override fun doInBackground(vararg params: Void?): String {
            //Thread.sleep(3000)
            textView.text ="From AsyncTask"
            return "hello"
        }
    }
}
