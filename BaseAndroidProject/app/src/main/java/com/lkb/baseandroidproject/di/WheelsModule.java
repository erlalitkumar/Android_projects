package com.lkb.baseandroidproject.di;

import com.lkb.baseandroidproject.car.Rims;
import com.lkb.baseandroidproject.car.Tires;
import com.lkb.baseandroidproject.car.Wheels;

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
