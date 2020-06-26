package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class LandingActivity : BaseActivity() {
    var backPressedCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.setName(this.localClassName)
        super.onCreate(savedInstanceState)
        title = this.localClassName
        setContentView(R.layout.activity_main)
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
            val intent = Intent(this, ScreenThree::class.java)
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

//    override fun onBackPressed() {
//        if (backPressedCount > 0) {
//            super.onBackPressed()
//        } else {
//            Toast.makeText(this, "press back again to exit", Toast.LENGTH_SHORT).show()
//            backPressedCount += 1
//        }
//
//    }
}
