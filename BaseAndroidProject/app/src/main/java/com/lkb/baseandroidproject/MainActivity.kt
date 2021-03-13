package com.lkb.baseandroidproject

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MyDialogFragment.DialogClickListener {
    var dialogFragment: MyDialogFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setTitle(R.string.app_name)
//        val db = Firebase.firestore
//        val city = hashMapOf(
//            "name" to "Baripada",
//            "state" to "Odisha",
//            "country" to "India",
//        )
//        val address = hashMapOf(
//            "data" to listOf(city, city)
//        )
//        db.collection("address").document("LA")
//            .set(address)
//            .addOnSuccessListener {
//                Log.d(
//                    "MainActivity",
//                    "DocumentSnapshot successfully written!"
//                )
//            }
//            .addOnFailureListener { e -> Log.w("MainActivity", "Error writing document", e) }
        logout.setOnClickListener {

            dialogFragment = MyDialogFragment()
            dialogFragment!!.setOnDialogClickListener(this)
            val fm = supportFragmentManager
            dialogFragment!!.show(fm, getString(R.string.dialog_tag))
        }
    }

    override fun onSuccess() {
        signOut()
    }

    override fun onFailure() {
        //do nothing
    }

    override fun onStop() {
        super.onStop()
        dialogFragment?.removeListener()
    }
}
