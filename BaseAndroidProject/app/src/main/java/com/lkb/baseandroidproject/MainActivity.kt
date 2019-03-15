package com.lkb.baseandroidproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.IOException
import java.util.*


class MainActivity : BaseActivity() {
    val tag = "MainActivity"
    var qrScan: IntentIntegrator? = null
    private var mAuth: FirebaseAuth? = null
    var userSnapshot: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qrScan = IntentIntegrator(this)

        mAuth = FirebaseAuth.getInstance()
        var userDatabase = FirebaseDatabase.getInstance().reference
        userSnapshot = userDatabase.child("users").child(mAuth!!.uid.toString())

        btnParent.setOnClickListener {
            qrScan?.initiateScan()
        }

        btnStartMap.setOnClickListener {
            var intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }

        btnChild.setOnClickListener {
            var intent = Intent(this@MainActivity, ChildQRCodeActivity::class.java)
            startActivity(intent)
        }


        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, CommunicationService::class.java)
            startService(intent)
        }
        stopServiceButton.setOnClickListener {
            val intent = Intent(this@MainActivity, CommunicationService::class.java)
            stopService(intent)
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 4)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this@MainActivity, "Result not found", Toast.LENGTH_SHORT).show()

            } else {
                txtChildId.text = result.contents
                //(application as MyApplication).childId = result.contents
                //update the child_id in the database
                (application as MyApplication).childId = result.contents
                var sPref = applicationContext.getSharedPreferences("Tracking",0) //private mode
                var editor = sPref.edit()
                editor.putString("child_id", result.contents)
                editor.commit()
                FirebaseDatabase.getInstance().reference
                    .child("users")
                    .child(FirebaseAuth.getInstance().uid.toString())
                    .setValue(UserModel(result.contents))
//                val valueEventListener = object : ValueEventListener {
//                    override fun onCancelled(e: DatabaseError) {
//                        Log.d(tag, "Error", e.toException())
//                    }
//
//                    override fun onDataChange(dataSnapshot: DataSnapshot) {
//                        if (dataSnapshot.exists())
//                        //update the child_id in the database
//                            FirebaseDatabase.getInstance().reference
//                                .child("users")
//                                .child(FirebaseAuth.getInstance().uid.toString())
//                                .setValue(UserModel(result.contents))
//                    }
//
//                }
//                userSnapshot?.addListenerForSingleValueEvent(valueEventListener)
            }
        }
    }
}
