package com.techyourchance.journeytodependencyinjection.screens.common.activities;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.MyApplication;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.CompositionRoot;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.Injector;
import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.PresentationCompositionRoot;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot mPresentationCompositionRoot;
    private boolean mIsInjectorUsed;

    @UiThread
    protected Injector getInjector() {
        if (mIsInjectorUsed) {
            throw new RuntimeException("there is not need to use injector more than once");
        }
        mIsInjectorUsed = true;
        return new Injector(getCompositionRoot());
    }

    private PresentationCompositionRoot getCompositionRoot() {
        if (mPresentationCompositionRoot == null) {
            mPresentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    this
            );
        }

        return mPresentationCompositionRoot;
    }

    private CompositionRoot getAppCompositionRoot() {
        return ((MyApplication) getApplication()).getCompositionRoot();
    }
}
