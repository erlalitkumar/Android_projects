package com.lkb.baseandroidproject

class MainPresenter(private val view:IMainPresenter.view):IMainPresenter{
    override fun onNetworkData(data: List<Station>) {
        view.onStationData(data)
    }

    override fun requestStationData() {
        val mediaUseCase = MediaUseCase(this)
        mediaUseCase.start()
    }

}