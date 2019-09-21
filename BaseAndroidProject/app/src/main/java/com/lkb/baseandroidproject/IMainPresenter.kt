package com.lkb.baseandroidproject

import com.lkb.baseandroidproject.model.StationList

interface IMainPresenter{
    fun requestStationData()
    fun onNetworkData(data: StationList)
    fun onPlayPause()
    interface View{
        fun onStationData(data: StationList)
    }

}