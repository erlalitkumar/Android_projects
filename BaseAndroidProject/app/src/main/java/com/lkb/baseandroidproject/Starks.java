package com.lkb.baseandroidproject;

import javax.inject.Inject;

public class Starks implements House {

    //Constructor injection
    @Inject
    public Starks() {

    }

    @Override
    public void prepareForWar() {
        System.out.println(this.getClass().getSimpleName()+" prepare for war");
    }

    @Override
    public void reportForWar() {
        System.out.println(this.getClass().getSimpleName()+"reporting..");
    }

    //method injection

}
