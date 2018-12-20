package com.techyourchance.journeytodependencyinjection;

import android.app.Application;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;

public class MyApplication extends Application {

    private CompositionRoot mCompositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        mCompositionRoot = new CompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        return mCompositionRoot;
    }
}
