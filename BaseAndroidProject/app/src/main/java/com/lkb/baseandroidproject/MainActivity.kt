package com.lkb.baseandroidproject

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lkb.baseandroidproject.databinding.ActivityMainBinding
import android.widget.Toast



class MainActivity : AppCompatActivity() {
private lateinit var handlers: MyClickHandlers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar);
        val usr = User("Lalit", "lalitkbehera@gmail.com")
        binding.user = usr
        handlers = MyClickHandlers(this)
        binding.handlers = handlers
    }


    inner class MyClickHandlers(internal var context: Context) {

        fun onFabClicked(view: View) {
            Toast.makeText(applicationContext, "FAB clicked!", Toast.LENGTH_SHORT).show()
        }

        fun onButtonClick(view: View) {
            Toast.makeText(applicationContext, "Button clicked!", Toast.LENGTH_SHORT).show()
        }

        fun onButtonClickWithParam(view: View, user: User) {
            Toast.makeText(applicationContext, "Button clicked! Name: " + user.name, Toast.LENGTH_SHORT).show()
        }

        fun onButtonLongPressed(view: View): Boolean {
            Toast.makeText(applicationContext, "Button long pressed!", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}
