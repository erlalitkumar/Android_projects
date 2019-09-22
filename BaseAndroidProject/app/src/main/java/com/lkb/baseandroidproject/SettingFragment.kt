package com.lkb.baseandroidproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.lkb.baseandroidproject.model.StationList
import kotlinx.android.synthetic.main.setting_main.*

class SettingFragment : Fragment(), IMainPresenter.View {
    private var presenter: MainPresenter? = null
    private var model: MediaStateViewModel? = null

    companion object {
        fun newInstance(): SettingFragment {
            return SettingFragment()
        }
    }

    override fun onStationData(data: StationList) {
        val stringJson = Gson().toJson(data)
        Log.d("Station", stringJson)
        writeFile("station.json", stringJson)
        Toast.makeText(activity, "Radio Station Updated", Toast.LENGTH_SHORT).show()
    }

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
        return inflater.inflate(R.layout.setting_main, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = MainPresenter(this)
        btnUpdateList.setOnClickListener {
            presenter?.requestStationData()
        }

        tvPrivacyPolicy.setOnClickListener {
            startActivity(Intent(activity,PrivacyPolicyActivity::class.java))
        }
    }

    private fun writeFile(filename: String, fileContents: String) {
        activity?.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it?.write(fileContents.toByteArray())
        }
    }
}