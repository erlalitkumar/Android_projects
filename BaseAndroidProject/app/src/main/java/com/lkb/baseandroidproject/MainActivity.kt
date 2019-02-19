package com.lkb.baseandroidproject


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val mContext = this@MainActivity
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener {
            mAuth!!.signInWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim())
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("MainActivity", "signInWithEmail:success")
                        val user = mAuth!!.getCurrentUser()
                        showToast("User Logged in!")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("MainActivity", "signInWithEmail:failure", it.exception)
                        showToast("Authentication failed!")
                        //updateUI(null)
                    }

                    // ...
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //val currentUser = mAuth!!.getCurrentUser()
        //updateUI(currentUser)
    }

    fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.logout -> {
                mAuth!!.addAuthStateListener {
                    if (it.currentUser == null) {
                        showToast("User logged out!")
                    }
                }
                mAuth!!.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
