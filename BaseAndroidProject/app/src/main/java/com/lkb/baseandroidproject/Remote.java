package com.lkb.baseandroidproject;

import android.util.Log;

import javax.inject.Inject;

public class Remote {
    @Inject
    public Remote() {
    }

    private static final String TAG = "Car";
    public void setListener(Car car) {
        Log.d(TAG, "Remote connected: ");
    }
}
