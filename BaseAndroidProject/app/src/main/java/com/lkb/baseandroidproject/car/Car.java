package com.lkb.baseandroidproject.car;

import android.util.Log;

import com.lkb.baseandroidproject.di.PerActivity;

import javax.inject.Inject;

@PerActivity
public class Car {
    private static final String TAG = "Car";
    private Engine engine;
    private Wheels wheels;
    private Driver driver;

    @Inject
    public Car(Engine engine, Wheels wheels, Driver driver) {
        this.engine = engine;
        this.wheels = wheels;
        this.driver = driver;
    }

    @Inject
    public void enableRemoate(Remote remote) {
        remote.setListener(this);
    }

    public void drive() {
        engine.start();
        Log.d(TAG, "driver :" + driver + "  driving ..." + this);
    }

}
