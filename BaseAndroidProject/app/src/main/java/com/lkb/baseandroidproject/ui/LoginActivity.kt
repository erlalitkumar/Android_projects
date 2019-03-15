package com.lkb.baseandroidproject.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lkb.baseandroidproject.R
import com.lkb.baseandroidproject.router.Router
import kotlinx.android.synthetic.main.login_main.*


class LoginActivity : BaseActivity(), GoogleApiClient.OnConnectionFailedListener {
    companion object {
        const val RC_SIGN_IN = 4
    }
    val tag = "LoginActivity"
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        FirebaseApp.initializeApp(this);
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            Router.startMainActivity(this@LoginActivity)
            finish()
        }
        val gso = initGso()
        val mGoogleSignInClient = gso?.let { GoogleSignIn.getClient(this, it) }

        btnGoogleSignIn.setOnClickListener {
            val signInIntent = mGoogleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onConnectionFailed(e: ConnectionResult) {
        Log.w(tag, "Google Api connection:failure ${e.errorMessage}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java) as GoogleSignInAccount
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(tag, "Google sign in failed", e)
            }

        }

    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(tag, "firebaseAuthWithGoogle:" + acct.id!!)
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(tag, "signInWithCredential:success")
                    Router.startMainActivity(this@LoginActivity)
                    finish()
                } else {
                    Log.w(tag, "signInWithCredential:failure", task.exception)
                }
            }
    }

    private fun initGso(): GoogleSignInOptions? {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
}