package com.lkb.baseandroidproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var component = DaggerBattleComponent.create()
        val war = component.war
        war.prepare()
        war.report()
    }
}
