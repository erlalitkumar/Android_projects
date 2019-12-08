package com.lkb.baseandroidproject.di;

import com.lkb.baseandroidproject.car.Driver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class DriverModule {
    @Singleton
    @Provides
    static Driver provideDriver() {
        return new Driver();
    }
}
