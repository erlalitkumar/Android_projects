package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.reactivex.rxjava3.disposables.Disposable

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: MainViewModel
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
