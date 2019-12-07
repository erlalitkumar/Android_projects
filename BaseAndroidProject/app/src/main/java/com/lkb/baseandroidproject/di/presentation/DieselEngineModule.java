package com.lkb.baseandroidproject.di.presentation;

import com.lkb.baseandroidproject.DieselEngine;
import com.lkb.baseandroidproject.Engine;
import com.lkb.baseandroidproject.PetrolEngine;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DieselEngineModule {
    @Binds
    abstract Engine provideEngine(DieselEngine engine);
}


//@Module
//public class PetrolEngineModule {
//    @Provides
//    Engine provideEngine(PetrolEngine engine){
//        return engine;
//    }
//}

