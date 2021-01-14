package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mViewModel: MainViewModel
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        textView.text = ""
        disposable = mViewModel.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.single())
            .subscribe({
                textView.text = it.toString();
            },
                { error -> textView.text = error.localizedMessage },
                { textView.text = "completed" }
            )

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
