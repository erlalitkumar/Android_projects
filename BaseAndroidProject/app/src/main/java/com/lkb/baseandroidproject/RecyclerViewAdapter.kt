package com.lkb.baseandroidproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bundle_item.view.*

class RecyclerViewAdapter(val width: Int, val height: Int) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    class MyViewHolder(val myView: View) : RecyclerView.ViewHolder(myView)

    var myData = mutableListOf<String>( "whats up ", "what are you doing")

//    init {
//        myData.addAll(myData)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bundle_item, parent, false)
        rootView.imageView2.layoutParams = ConstraintLayout.LayoutParams((width / 4), (width / 4))
        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //holder.myView.tvItem.text = myData[position]
    }

    override fun getItemCount(): Int {
        return myData.size
    }


}