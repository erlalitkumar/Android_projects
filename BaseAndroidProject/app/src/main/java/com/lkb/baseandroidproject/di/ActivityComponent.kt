package com.lkb.baseandroidproject.di

import com.lkb.baseandroidproject.car.Car
import com.lkb.baseandroidproject.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import javax.inject.Named

@PerActivity
@Subcomponent(
    modules = [WheelsModule::class, DieselEngineModule::class]
)
interface ActivityComponent {
    fun getCar(): Car
    fun inject(mainActivity: MainActivity)

//    @Component.Builder
//    interface Builder {
//
//        @BindsInstance
//        fun horsePower(@Named("horse power") horsePower: Int): Builder
//
//        @BindsInstance
//        fun engineCapacity(@Named("engine capacity") engineCapacity: Int): Builder
//
//        fun appComponent(appComponent: AppComponent): Builder
//
//        fun build(): ActivityComponent
//    }
}