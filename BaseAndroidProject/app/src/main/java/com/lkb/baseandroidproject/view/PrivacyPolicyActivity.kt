package com.lkb.baseandroidproject.view

import android.os.Bundle
import com.lkb.baseandroidproject.Constants.Companion.STATION_JSON_FILE_URL
import com.lkb.baseandroidproject.R
import com.lkb.baseandroidproject.view.base.BaseActivity
import kotlinx.android.synthetic.main.privacy_policy.*

class PrivacyPolicyActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.privacy_policy)
        wvPrivacyPolicy.loadUrl(STATION_JSON_FILE_URL+"privacy_policy.html")
    }
}