package com.lkb.baseandroidproject

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable

class MainViewModel : ViewModel() {

    private val wikiApiService by lazy {
        WikiApiService.create()
    }

    fun getData(): Observable<String> {
        return Observable.just("hello", "hi", "there")
    }

    fun callHitcountWikiApi(searchString:String):Observable<WikiModel.Result>{
        return wikiApiService.hitCountCheck("query", "json", "search", searchString)
    }

}