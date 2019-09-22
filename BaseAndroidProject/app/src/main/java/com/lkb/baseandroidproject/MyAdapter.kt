package com.lkb.baseandroidproject

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.lkb.baseandroidproject.model.Station
import com.lkb.baseandroidproject.model.StationList
import es.claucookie.miniequalizerlibrary.EqualizerView

class MyAdapter() :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var myDataSet: StationList

    constructor(myDataSet: StationList) : this() {
        this.myDataSet = myDataSet
    }

    var currentPlayingStationPosition = -1
    private lateinit var listener: RecyclerViewClickListener

    interface RecyclerViewClickListener {
        fun onClick(item: Station)
    }

    fun setRecyclerViewClickListener(listener: RecyclerViewClickListener) {
        this.listener = listener
    }

    inner class MyViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.info_text)
        val playImage: ImageView = view.findViewById(R.id.playImg)
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

        holder.textView.text = myDataSet.stationList[position].title
        if (currentPlayingStationPosition != -1) {
            myDataSet.stationList[currentPlayingStationPosition].isPlaying = true
            holder.equalizer.setBackgroundColor(Color.RED)
            holder.equalizer.visibility = View.VISIBLE
            holder.equalizer.animateBars()
        }
        if (!myDataSet.stationList[position].isPlaying) {
            holder.playImage.setImageResource(R.drawable.ic_play_icon)
            holder.equalizer.visibility = View.GONE
        } else {
            holder.playImage.setImageResource(R.drawable.ic_stop_icon)
        }
        holder.playImage.setOnClickListener {
            if (currentPlayingStationPosition != -1 && currentPlayingStationPosition != position) {
                myDataSet.stationList[currentPlayingStationPosition].isPlaying = false
                notifyDataSetChanged()
            }
            listener.onClick(myDataSet.stationList[position])
            currentPlayingStationPosition =
                if (currentPlayingStationPosition == position) -1 else position



            if (!myDataSet.stationList[position].isPlaying) {
                holder.playImage.setImageResource(R.drawable.ic_stop_icon)
                myDataSet.stationList[position].isPlaying = true
                holder.equalizer.visibility = View.VISIBLE
                holder.equalizer.setBackgroundColor(Color.RED)
                holder.equalizer.animateBars()
            } else {
                holder.playImage.setImageResource(R.drawable.ic_play_icon)
                myDataSet.stationList[position].isPlaying = false
                holder.equalizer.stopBars()
                holder.equalizer.visibility = View.GONE
            }

        }
    }

    override fun getItemCount() = myDataSet.stationList.size
    fun getCurrentPosition(): Int {
        return currentPlayingStationPosition
    }
}
