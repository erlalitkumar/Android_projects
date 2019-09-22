package com.lkb.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lkb.baseandroidproject.Constants.Companion.STATION_JSON_FILE_URL
import kotlinx.android.synthetic.main.privacy_policy.*

class PrivacyPolicyActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_policy)
        wvPrivacyPolicy.loadUrl(STATION_JSON_FILE_URL+"privacy_policy.html")
    }
}