//package com.lkb.baseandroidproject
//
//import android.support.v7.widget.RecyclerView
//import android.view.View
//import android.view.ViewGroup
//
//class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
//    var data: ArrayList<User> = arrayListOf()
//    override fun onCreateViewHolder(parent: ViewGroup, postion: Int): MyViewHolder {
//return MyViewHolder()
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//
//    }
//
//    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
//
//    fun bindData(data: ArrayList<User>) {
//        this.data = data
//    }
//}