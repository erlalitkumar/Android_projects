package com.lkb.baseandroidproject.di.presentation;

import com.lkb.baseandroidproject.Rims;
import com.lkb.baseandroidproject.Tires;
import com.lkb.baseandroidproject.Wheels;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class WheelsModule {
    @Provides
    static Rims provideRims() {
        return new Rims();
    }

    @Provides
    static Tires provideTires() {
        Tires tires = new Tires();
        tires.inflate();
        return tires;
    }

    @Provides
    static Wheels provideWheels(Rims rims, Tires tires){
        return new Wheels(rims,tires);
    }
}
