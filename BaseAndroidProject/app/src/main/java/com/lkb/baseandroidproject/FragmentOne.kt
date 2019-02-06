package com.lkb.baseandroidproject

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_one.*

class FragmentOne : Fragment() {
    lateinit var listener: OnButtonClickListener
    fun setOnButtonClickListenr(activity: Activity) {
        listener = activity as MainActivity
    }

    interface OnButtonClickListener {
        fun buttonClicked(data:String)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.fragment_one, container, false)
        val buttonOne:Button = fragmentView.findViewById(R.id.button)
        buttonOne.setOnClickListener {
            listener.buttonClicked("Button one Clicked")
        }
        return fragmentView
    }

}