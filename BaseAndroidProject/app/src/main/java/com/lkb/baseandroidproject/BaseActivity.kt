package com.lkb.baseandroidproject

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.lkb.baseandroidproject.di.presentation.PresentationComponent
import com.lkb.baseandroidproject.di.presentation.PresentationModule
import java.lang.RuntimeException

open class BaseActivity:AppCompatActivity() {
    var mIsInjectorUsed: Boolean = false
//    @UiThread
//    protected fun getPresentationComponent(): PresentationComponent {
//        if (mIsInjectorUsed) {
//            throw RuntimeException("There is no need to use injector more than once")
//        }
//        mIsInjectorUsed = true
//        return getApplicationComponent().newPresentationComponent(
//            PresentationModule(
//                this
//            )
//        )
//    }

//    private fun getApplicationComponent(): ApplicationComponent {
//        return (application as BaseApplication).getApplicationComponent()
//    }
}