package com.lkb.baseandroidproject

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: MainViewModel
    lateinit var disposable: Disposable
    lateinit var wikiDisposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        textView.text = ""
        disposable = mViewModel.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                textView.text = it.toString();
            }

        val clickListener = View.OnClickListener{
            when(it.id){
                R.id.searchButton->{
                    val searchTextString = searchText.text.toString()
                    wikiDisposable = mViewModel.callHitcountWikiApi(searchTextString)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            { result -> hitCountText.text = "${result.query.searchinfo.totalhits} result found" },
                            { error -> Log.i("error", error.toString())//Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                            }
                        )
                }
            }
        }
        searchButton.setOnClickListener(clickListener)



    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
