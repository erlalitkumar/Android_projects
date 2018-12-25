package com.lkb.base.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lkb.base.R
import com.lkb.base.presenter.ILoginPresenter
import com.lkb.base.presenter.LoginPresenter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), ILoginView {
    override fun onLoginError(msg: String) {
        Toasty.error(this, msg).show()
    }

    lateinit var loginPresenter: ILoginPresenter
    override fun onLoginSuccess(msg: String) {
        Toasty.success(this, msg).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginPresenter = LoginPresenter(this)

        loginBtn.setOnClickListener { loginPresenter.onLogin(emailEdit.text.toString(), passwordEdit.text.toString()) }
    }
}
