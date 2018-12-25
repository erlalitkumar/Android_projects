package com.lkb.base.model

import android.text.TextUtils
import android.util.Patterns
import com.lkb.base.model.IUser

class User(email: String, password: String) : IUser {
    var mEmail = email
    var mPassword = password
    override fun getEmail(): String {
        return mEmail
    }

    override fun getPassword(): String {
        return mPassword
    }

    override fun isValidData(): Int {
        return if (TextUtils.isEmpty(getEmail())) 0
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) 1
        else if (getPassword().length <= 6) 2
        else -1
    }
}