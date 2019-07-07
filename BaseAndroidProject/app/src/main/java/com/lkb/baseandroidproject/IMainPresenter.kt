package com.lkb.baseandroidproject

interface IMainPresenter{
    fun requestStationData()
    fun onNetworkData(data:List<Station>)
    interface view{
        fun onStationData(data:List<Station>)
    }

}