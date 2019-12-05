package com.lkb.baseandroidproject;

import javax.inject.Inject;

public class Wheels {
    // pretend like we don't own this class
    private Rims rims;
    private Tires tires;
    public Wheels(Rims rims, Tires tires) {
        this.rims = rims;
        this.tires = tires;
    }
}
