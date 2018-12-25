package com.lkb.base.presenter

import com.lkb.base.model.User
import com.lkb.base.view.ILoginView

class LoginPresenter(loginView: ILoginView) : ILoginPresenter {

    var mLoginView = loginView

    override fun onLogin(email: String, password: String) {
        var user = User(email, password)
        var loginCode = user.isValidData()
        when (loginCode) {
            0 -> mLoginView.onLoginError("you must enter your email")
            1 -> mLoginView.onLoginError("you must enter valid email")
            2 -> mLoginView.onLoginError("Password length must be greater than 6")
            else -> mLoginView.onLoginError("Login Successful")
        }
    }

}