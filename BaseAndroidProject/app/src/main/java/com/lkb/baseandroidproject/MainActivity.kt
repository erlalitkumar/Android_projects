package com.lkb.baseandroidproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fm = supportFragmentManager
        val ft= fm.beginTransaction()
        ft.add(R.id.containerOne,FragmentOne())
        ft.add(R.id.containerTwo,FragmentTwo())
        ft.commit()
    }
}
