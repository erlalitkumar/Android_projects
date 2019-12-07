package com.lkb.baseandroidproject.di.presentation;

import com.lkb.baseandroidproject.Engine;
import com.lkb.baseandroidproject.PetrolEngine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

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

