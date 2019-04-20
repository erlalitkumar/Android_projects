package com.lkb.baseandroidproject

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.StaggeredGridLayoutManager


class MainActivity : AppCompatActivity() {
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: StaggeredGridLayoutManager

    companion object {
        var TAG = "MainActivity"
    }

    private var mMusicService: MusicService? = null
    private var mServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "Music service disconnected.")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMusicService = (service as MusicService.LocalBinder).service
            Log.d(TAG, "Music service Connected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //doBindService()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
        mToolbar.setBackgroundColor(Color.TRANSPARENT)

        viewManager = staggeredGridLayoutManager
        var myDataset = listOf<String>(
            "Radio city Hindi",
            "Radio city International ",
            "Radio city Classic",
            "Radio city Hindi",
            "Radio city Hindi",
            "Radio city International ",
            "Radio city Classic",
            "Radio city Hindi",
            "Radio city Hindi",
            "Radio city International ",
            "Radio city Classic",
            "Radio city Hindi",
            "Radio city Hindi",
            "Radio city International ",
            "Radio city Classic",
            "Radio city Hindi"
        )
        viewAdapter = MyAdapter(myDataset)

        (mRecyclerView as RecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }

        btnChannel.setOnClickListener {
            var intent = Intent(this@MainActivity, MusicService::class.java)
            intent.putExtra("channel", "http://prclive1.listenon.in:9960/;")
            startService(intent)
        }

        btnChannel3.setOnClickListener {
            var intent = Intent(this@MainActivity, MusicService::class.java)
            intent.putExtra("channel", "https://prclive4.listenon.in/International")
            startService(intent)
        }
    }

    private fun doBindService() {
        startService(Intent(this@MainActivity, MusicService::class.java))
        bindService(
            Intent(
                this@MainActivity, MusicService::class.java
            ), mServiceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun doUnbindService() {
        unbindService(mServiceConnection)
    }

    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
    }
}

class MyAdapter(private val myDataset: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    inner class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.info_text)
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = myDataset[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
