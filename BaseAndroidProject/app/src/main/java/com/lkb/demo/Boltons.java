package com.lkb.demo;

import dagger.Module;
import dagger.Provides;

import javax.inject.Inject;

/**
 * Created by Hari on 04/11/17.
 */
public class Boltons implements House {

    @Inject
    public Boltons(){

    }

    @Override
    public void prepareForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName()+" prepared for war");
    }

    @Override
    public void reportForWar() {
        //do something
        System.out.println(this.getClass().getSimpleName()+" reporting..");
    }
}
