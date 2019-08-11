package com.lkb.baseandroidproject

interface IMainPresenter{
    fun requestStationData()
    fun onNetworkData(data:StationList)
    interface View{
        fun onStationData(data:StationList)
    }

}