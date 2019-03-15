package com.lkb.baseandroidproject.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.lkb.baseandroidproject.*
import com.lkb.baseandroidproject.model.UserModel
import com.lkb.baseandroidproject.router.Router
import com.lkb.baseandroidproject.service.CommunicationService
import com.lkb.baseandroidproject.service.LocationService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    companion object {
       const val TAG = "MainActivity"
    }

    var qrScan: IntentIntegrator? = null
    private var mAuth: FirebaseAuth? = null
    var userSnapshot: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrScan = IntentIntegrator(this)

        mAuth = FirebaseAuth.getInstance()
        var userDatabase = FirebaseDatabase.getInstance().reference
        userSnapshot = userDatabase.child("users").child(mAuth?.uid.toString())

        btnParent.setOnClickListener {
            qrScan?.initiateScan()
        }

        btnStartMap.setOnClickListener {
            Router.startMapsActivity(this@MainActivity)
        }

        btnChild.setOnClickListener {
            Router.startChildQRCodeActivity(this@MainActivity)
        }


        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Router.startLoginActivity(this@MainActivity)
        }

        button.setOnClickListener {
            when {
                (application as MyApplication).role == "Child" -> {
                    val intent = Intent(this@MainActivity, LocationService::class.java)
                    startService(intent)
                }
                else -> {
                    val intent = Intent(this@MainActivity, CommunicationService::class.java)
                    startService(intent)
                }
            }

        }
        stopServiceButton.setOnClickListener {
            when {
                (application as MyApplication).role == "Child" -> {
                    val intent = Intent(this@MainActivity, LocationService::class.java)
                    stopService(intent)
                }
                else -> {
                    val intent = Intent(this@MainActivity, CommunicationService::class.java)
                    stopService(intent)
                }
            }
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
                (application as MyApplication).childId = result.contents
                var sPref = applicationContext.getSharedPreferences("Tracking",0) //private mode
                var editor = sPref.edit()
                editor.putString("child_id", result.contents)
                editor.commit()
                FirebaseDatabase.getInstance().reference
                    .child("users")
                    .child(FirebaseAuth.getInstance().uid.toString())
                    .setValue(UserModel(result.contents))
            }
        }
    }
}
