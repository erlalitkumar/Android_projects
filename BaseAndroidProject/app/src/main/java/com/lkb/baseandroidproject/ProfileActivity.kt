package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.profile_main.*

class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_main)
        var intent = intent
        val profileName = intent.getStringExtra("ProfileName")
        tvUserName.text = profileName
        btnSignOut.setOnClickListener{
            FirebaseAuth.getInstance()!!.signOut()
            var intent = Intent(this@ProfileActivity,MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
