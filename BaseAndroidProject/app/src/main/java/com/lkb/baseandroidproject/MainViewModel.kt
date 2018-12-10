package com.lkb.baseandroidproject

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

class MainViewModel : ViewModel() {
    fun getData(): Observable<String> {
        return Observable.just("hello", "hi", "there")
    }

}