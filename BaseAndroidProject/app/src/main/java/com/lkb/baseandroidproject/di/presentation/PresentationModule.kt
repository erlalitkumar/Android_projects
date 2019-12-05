package com.lkb.baseandroidproject.di.presentation

import android.app.Activity
import android.view.LayoutInflater
import com.lkb.baseandroidproject.view.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(baseActivity: BaseActivity) {
    private var mActivity = baseActivity

    @Provides
    fun getFragmentManager(): androidx.fragment.app.FragmentManager? {
        return mActivity.supportFragmentManager
    }

    @Provides
    fun getLayoutInflater(): LayoutInflater? {
        return LayoutInflater.from(mActivity)
    }

    @Provides
    fun getActivity(): Activity {
        return mActivity
    }
}