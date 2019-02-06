package com.lkb.baseandroidproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.create_user_layout.*

class UserCreateActivity:AppCompatActivity(){

    private val tag = "UserCreateActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_layout)

        addUserButton.setOnClickListener{
            Log.d(tag,"firstName:${firstName.text}")
            Log.d(tag,"lastName:${lastName.text}")
            Log.d(tag,"email:${email.text}")
        }
    }
}