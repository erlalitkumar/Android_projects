package com.lkb.baseandroidproject

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

/**
 * This service provides the current latitude and longitude.
 */
class CommunicationService : Service() {

    private var instance: CommunicationService? = null
    private val msg_root = "messages"
    private val tag = "ComService"
    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null
    private lateinit var comDataReference: DatabaseReference

    private fun isServiceRunning(): Boolean {
        return instance != null
    }

    // Handler that receives messages from the thread.
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {
        @SuppressLint("MissingPermission")
        override fun handleMessage(msg: Message?) {
            //create the communication model in firebase and observe any change.
            // comDataReference.child(msg_root).child(FirebaseAuth.getInstance().uid.toString())
            //  .setValue(ComModel())


        }
    }

    override fun onCreate() {
        comDataReference = FirebaseDatabase.getInstance().reference
        Log.v(tag, FirebaseAuth.getInstance().uid)
        val comDataListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                //val post = dataSnapshot.getValue(Post::class.java)
                // ...
                val data = dataSnapshot.getValue(ComModel::class.java)
                Toast.makeText(this@CommunicationService, " current lat${data?.cLat} and long${data?.cLong}", Toast.LENGTH_SHORT).show()
                Log.w(tag, "${data.toString()}")
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(tag, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        comDataReference.child("messages").child((application as MyApplication).childId)
            .addValueEventListener(comDataListener)
        HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND).apply {
            start()
            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(tag, "onStartCommand executed");

        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        if (!isServiceRunning()) {
            Log.d(tag, "onStartCommand executed with job");
            serviceHandler?.obtainMessage()?.also { msg ->
                msg.arg1 = startId
                serviceHandler?.sendMessage(msg)
            }
            instance = this@CommunicationService
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    override fun onDestroy() {
        Log.v(tag, "Location Service destroyed")
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
        instance = null
    }
}