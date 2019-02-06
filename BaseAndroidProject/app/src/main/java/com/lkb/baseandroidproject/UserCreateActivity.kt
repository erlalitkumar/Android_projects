package com.lkb.baseandroidproject

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.create_user_layout.*

class UserCreateActivity:AppCompatActivity(){

    private val tag = "UserCreateActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_user_layout)
        val db:AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "production"
        ).allowMainThreadQueries().build()
        addUserButton.setOnClickListener{
//            Log.d(tag,"firstName:${firstName.text}")
//            Log.d(tag,"lastName:${lastName.text}")
//            Log.d(tag,"email:${email.text}")
            val user = User(firstName.text.toString(),
                lastName.text.toString(),
                email.text.toString())
            db.userDao().insertAll(user)
            //finish()
        }
    }
}