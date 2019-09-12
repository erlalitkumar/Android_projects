package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.privacy_policy.*

class PrivacyPolicyActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_policy)
        wvPrivacyPolicy.loadUrl("https://lalitbehera.github.io/privacy_policy.html")
    }
}