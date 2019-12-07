package com.lkb.baseandroidproject;

import android.util.Log;

import javax.inject.Inject;

public class Car {
    private static final String TAG = "Car";
    @Inject
    Engine engine;
    Wheels wheels;

    @Inject
    public Car(Wheels wheels) {
        //this.engine = engine;
        this.wheels = wheels;
    }

    @Inject
    public void enableRemoate(Remote remote) {
        remote.setListener(this);
    }

    void drive() {
        engine.start();
        Log.d(TAG, "driving ...");
    }

}
