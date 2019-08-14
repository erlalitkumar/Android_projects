package com.lkb.baseandroidproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class SettingFragment:Fragment(){
    private var model:MediaStateViewModel?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = (activity?.run {
            ViewModelProviders.of(this)[MediaStateViewModel::class.java]
        } ?: throw Exception("Invalid Activity"))
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.setting_main,null,false)
    }

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }
}