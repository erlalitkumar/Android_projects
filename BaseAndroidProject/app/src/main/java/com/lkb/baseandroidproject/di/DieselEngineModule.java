package com.lkb.baseandroidproject.di;

import com.lkb.baseandroidproject.car.DieselEngine;
import com.lkb.baseandroidproject.car.Engine;

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

