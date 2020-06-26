package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.screen_three.*
import kotlinx.android.synthetic.main.screen_three.button1
import kotlinx.android.synthetic.main.screen_three.button2
import kotlinx.android.synthetic.main.screen_three.button3
import kotlinx.android.synthetic.main.screen_three.chronometer

class ScreenThree :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.setName(this.localClassName)
        super.onCreate(savedInstanceState)
        title = this.localClassName
        setContentView(R.layout.screen_three)
        button1.setOnClickListener {
            val intent = Intent(this, ScreenOne::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, ScreenTwo::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent = Intent(this, LandingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        chronometer.start()
    }

    override fun onPause() {
        super.setScreenTime(chronometer.text.toString())
        super.onPause()
        chronometer.stop()
    }
}