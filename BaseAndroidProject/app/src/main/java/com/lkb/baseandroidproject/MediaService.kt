package com.lkb.baseandroidproject

import retrofit2.Call
import retrofit2.http.GET

interface MediaService{
    @GET("/station.json")
    fun getStations(): Call<StationList>
}