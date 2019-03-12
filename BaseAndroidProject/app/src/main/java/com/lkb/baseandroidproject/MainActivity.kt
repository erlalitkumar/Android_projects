package com.lkb.baseandroidproject


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.internal.FirebaseAppHelper.getUid
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private val mContext = this
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showToast(msg: String) {
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
                FirebaseAuth.getInstance()!!.addAuthStateListener {
                    if (it.currentUser == null) {
                        showToast("User logged out!")
                    }
                }
                FirebaseAuth.getInstance()!!.signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun writeNewUser(uid: String, message: String, childId: String) {
        val user = User(uid, "hello","4555er5r221")
        database.child("users").child(uid).setValue(user)
    }

    override fun onResume() {
        super.onResume()
        if(FirebaseAuth.getInstance().currentUser==null){
            login.setOnClickListener {
                //mAuth!!.getAccessToken(true)
                FirebaseAuth.getInstance()!!.signInWithEmailAndPassword(email.text.toString().trim(), password.text.toString().trim())
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "signInWithEmail:success")
                            //val user = FirebaseAuth.getInstance().currentUser
                            database = FirebaseDatabase.getInstance().reference
                            //writeNewUser(userid,"hi","")
                            //val user = mAuth!!.currentUser
                            var intent = Intent(this@MainActivity,ProfileActivity::class.java)
                            intent.putExtra("ProfileName",FirebaseAuth.getInstance().currentUser!!.email)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithEmail:failure", it.exception)
                            showToast("Authentication failed!")
                            //updateUI(null)
                        }
                    }
            }
        }else{
            var intent = Intent(this@MainActivity,ProfileActivity::class.java)
            intent.putExtra("ProfileName", FirebaseAuth.getInstance().currentUser!!.email)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
    }
}
