package com.lkb.baseandroidproject

import com.lkb.baseandroidproject.model.StationList

class MainPresenter(private val view:IMainPresenter.View):IMainPresenter{
    override fun onNetworkData(data: StationList) {
        view.onStationData(data)
    }

    override fun requestStationData() {
        val mediaUseCase = MediaUseCase(this)
        mediaUseCase.start()
    }

}