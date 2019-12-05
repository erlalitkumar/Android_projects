package com.lkb.baseandroidproject.network

import com.lkb.baseandroidproject.model.StationList
import retrofit2.Call
import retrofit2.http.GET

interface MediaService{
    @GET("/station.json")
    fun getStations(): Call<StationList>
}