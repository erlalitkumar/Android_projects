package com.lkb.baseandroidproject

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.MyHolder>(),
    BindableAdapter<Long> {
    override fun setData(items: List<Long>) {
        userIds = items
        notifyDataSetChanged()
    }

    override fun changedPositions(positions: Set<Int>) {
        positions.forEach(this::notifyItemChanged)
    }

    var userIds = emptyList<Long>()

    class MyHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(userId: Long) {
            itemView.userText.text = "user id: $userId"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, posiotn: Int): RecyclerViewAdapter.MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return userIds.size
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyHolder, position: Int) {
        holder.bind(userIds[position])
    }

}