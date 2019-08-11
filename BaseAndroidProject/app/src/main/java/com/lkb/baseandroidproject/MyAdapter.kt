package com.lkb.baseandroidproject

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import es.claucookie.miniequalizerlibrary.EqualizerView

class MyAdapter(private val myDataset: StationList) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var currentPlayingStationPosition = -1
    private lateinit var listener: RecyclerViewClickListener

    interface RecyclerViewClickListener {
        fun onClick(item: Station)
    }

    fun setRecyclerViewClickListener(listener: RecyclerViewClickListener) {
        this.listener = listener
    }

    inner class MyViewHolder(val view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.info_text)
        val playImage: ImageView = view.findViewById(R.id.playImage)
        val equalizer: EqualizerView = view.findViewById(R.id.equalizerView)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_text_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textView.text = myDataset.stationList[position].title
        if (currentPlayingStationPosition != -1) {
            myDataset.stationList[currentPlayingStationPosition].isPlaying = true
            holder.equalizer.setBackgroundColor(Color.RED)
            holder.equalizer.visibility = View.VISIBLE
            holder.equalizer.animateBars()
        }
        if (!myDataset.stationList[position].isPlaying) {
            holder.playImage.setImageResource(R.drawable.ic_play_icon)
            holder.equalizer.visibility = View.GONE
        } else {
            holder.playImage.setImageResource(R.drawable.ic_stop_icon)
        }
        holder.playImage.setOnClickListener {
            if (currentPlayingStationPosition != -1 && currentPlayingStationPosition != position) {
                myDataset.stationList[currentPlayingStationPosition].isPlaying = false
                notifyDataSetChanged()
            }
            listener.onClick(myDataset.stationList[position])
            currentPlayingStationPosition = position

            if (!myDataset.stationList[position].isPlaying) {
                holder.playImage.setImageResource(R.drawable.ic_stop_icon)
                myDataset.stationList[position].isPlaying = true
                holder.equalizer.visibility = View.VISIBLE
                holder.equalizer.setBackgroundColor(Color.RED)
                holder.equalizer.animateBars()
            } else {
                holder.playImage.setImageResource(R.drawable.ic_play_icon)
                myDataset.stationList[position].isPlaying = false
                holder.equalizer.stopBars()
                holder.equalizer.visibility = View.GONE
            }

        }
    }

    override fun getItemCount() = myDataset.stationList.size

    fun updateData() {

    }
}
