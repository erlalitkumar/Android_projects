package com.lkb.baseandroidproject.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.lkb.baseandroidproject.Constants.Companion.STATION_JSON_FILE_URL
import com.lkb.baseandroidproject.IMainPresenter
import com.lkb.baseandroidproject.model.StationList


class MediaUseCase(private val presenter: IMainPresenter) : Callback<StationList> {
    override fun onFailure(call: Call<StationList>, t: Throwable) {
        t.printStackTrace()
    }

    override fun onResponse(call: Call<StationList>, response: Response<StationList>) {
        if (response.isSuccessful) {
            val station: StationList? = response.body()
            station?.let { presenter.onNetworkData(it) }
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

        val stationApi: MediaService = retrofit.create(
            MediaService::class.java!!)

        val call = stationApi.getStations()
        call.enqueue(this)
    }

    companion object {
        const val BASE_URL = STATION_JSON_FILE_URL
    }


}