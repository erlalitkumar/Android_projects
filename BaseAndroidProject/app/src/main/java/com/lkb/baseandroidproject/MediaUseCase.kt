package com.lkb.baseandroidproject

import android.provider.MediaStore
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder


class MediaUseCase(val presenter: IMainPresenter) : Callback<StationList> {
    override fun onFailure(call: Call<StationList>, t: Throwable) {
        t.printStackTrace()
    }

    override fun onResponse(call: Call<StationList>, response: Response<StationList>) {
        if (response.isSuccessful) {
            val station: StationList? = response.body()
            station?.stationList?.let { presenter.onNetworkData(it) }
        }
    }

    fun start() {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val stationApi: MediaService = retrofit.create(MediaService::class.java!!)

        val call = stationApi.getStations()
        call.enqueue(this)
    }

    companion object {
        const val BASE_URL = "https://lalitbehera.github.io/"
    }


}