package com.lkb.baseandroidproject.di.presentation

import com.lkb.baseandroidproject.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent{
    fun inject(activity: MainActivity)
}