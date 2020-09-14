package com.lkb.baseandroidproject

import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView


class ImageAdapter     // Constructor
    (private val mContext: Context) : BaseAdapter() {
    override fun getCount(): Int {
        return mThumbIds.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    // create a new ImageView for each item referenced by the Adapter
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val imageView: ImageView
        val metrics: DisplayMetrics = mContext.resources.displayMetrics
        val dp = 120f
        val fpixels = metrics.density * dp
        val pixels = (fpixels + 0.5f).toInt()
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = AbsListView.LayoutParams(
                pixels,
                pixels
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(8, 8, 8, 8)
        } else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(mThumbIds[position])
        return imageView
    }

    // Keep all Images in array
    var mThumbIds = arrayOf<Int>(
        R.drawable.chicks, R.drawable.white_dog,
        R.drawable.small_robot, R.drawable.chicks, R.drawable.white_dog,
        R.drawable.small_robot, R.drawable.chicks, R.drawable.white_dog,
        R.drawable.small_robot, R.drawable.chicks, R.drawable.white_dog,
        R.drawable.small_robot
    )
}