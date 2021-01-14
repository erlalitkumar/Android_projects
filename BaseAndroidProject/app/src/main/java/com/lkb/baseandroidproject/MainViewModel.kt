package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable

class MainViewModel : ViewModel() {
    fun getData(): Observable<String> {
        //return Observable.just("hello", "hi", "there").delay(3000,TimeUnit.MILLISECONDS)
        return Observable.range(1,100)
            .flatMap {
                Thread.sleep(1000)
                Observable.just(it.toString())
            }

    }

}