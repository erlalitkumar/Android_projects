package com.lkb.baseandroidproject.di.presentation

import com.lkb.baseandroidproject.view.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent{
    fun inject(activity: MainActivity)
}