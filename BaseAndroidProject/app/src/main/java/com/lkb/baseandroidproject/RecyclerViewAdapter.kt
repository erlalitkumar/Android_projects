package com.lkb.baseandroidproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var data: List<User> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, postion: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.firstName.text = data.get(position).firstName
        holder.lastName.text = data.get(position).lastName
        holder.email.text = data.get(position).email
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstName: TextView = itemView.findViewById(R.id.firstName)
        var lastName: TextView = itemView.findViewById(R.id.lastName)
        var email: TextView = itemView.findViewById(R.id.email)
    }

    fun bindData(data: List<User>) {
        this.data = data
        notifyDataSetChanged()
    }
}