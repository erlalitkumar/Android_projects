package com.lkb.baseandroidproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = ""
        disposable = Observable.just("hello", "hi", "there")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                textView.text = it.toString();
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
