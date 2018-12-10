package com.lkb.baseandroidproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""
        // producer
        var observable = Observable.just("hello", "hi","there")

        // consumer
        val consumer = this::updateUi

        observable.subscribe(consumer)
    }

    fun updateUi(string:String){
        textView.text = string;
    }
}
