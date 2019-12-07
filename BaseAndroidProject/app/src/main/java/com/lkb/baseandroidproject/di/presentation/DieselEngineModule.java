package com.lkb.baseandroidproject.di.presentation;

import com.lkb.baseandroidproject.DieselEngine;
import com.lkb.baseandroidproject.Engine;
import com.lkb.baseandroidproject.PetrolEngine;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public  class DieselEngineModule {
    private int horsePower;

    public DieselEngineModule(int horsePower) {
        this.horsePower = horsePower;
    }

    @Provides
    int provideHorsePower(){
        return horsePower;
    }

    @Provides
     Engine provideEngine(DieselEngine engine){
        return engine;
    }
}


//@Module
//public class PetrolEngineModule {
//    @Provides
//    Engine provideEngine(PetrolEngine engine){
//        return engine;
//    }
//}

