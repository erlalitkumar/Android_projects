package com.lkb.baseandroidproject

import com.lkb.baseandroidproject.model.StationList
import com.lkb.baseandroidproject.network.MediaUseCase

class MainPresenter(private val view:IMainPresenter.View):IMainPresenter{
    override fun onPlayPause() {

    }

    override fun onNetworkData(data: StationList) {
        view.onStationData(data)
    }

    override fun requestStationData() {
        val mediaUseCase = MediaUseCase(this)
        mediaUseCase.start()
    }

}