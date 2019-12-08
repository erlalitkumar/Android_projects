package com.lkb.baseandroidproject.di;

import com.lkb.baseandroidproject.car.Engine;
import com.lkb.baseandroidproject.car.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class PetrolEngineModule {
    @Binds
    abstract Engine provideEngine(PetrolEngine engine);
}


//@Module
//public class PetrolEngineModule {
//    @Provides
//    Engine provideEngine(PetrolEngine engine){
//        return engine;
//    }
//}

