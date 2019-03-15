package com.lkb.baseandroidproject.router

import android.content.Context
import android.content.Intent
import com.lkb.baseandroidproject.ui.ChildQRCodeActivity
import com.lkb.baseandroidproject.ui.LoginActivity
import com.lkb.baseandroidproject.ui.MainActivity
import com.lkb.baseandroidproject.ui.MapsActivity

class Router {
    companion object {
        fun startMainActivity(context: Context){
            var intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

        fun startLoginActivity(context: Context){
            var intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(intent)
        }

        fun startChildQRCodeActivity(context: Context){
            var intent = Intent(context, ChildQRCodeActivity::class.java)
            context.startActivity(intent)
        }

        fun startMapsActivity(context: Context){
            var intent = Intent(context, MapsActivity::class.java)
            context.startActivity(intent)
        }

    }

}