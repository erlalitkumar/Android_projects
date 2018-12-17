package com.lkb;

import io.reactivex.Flowable;

public class Demo {
    public static void main(String[] args) {
        Flowable.just(1,2,3,4,5)
                .subscribe(System.out::println);
    }
}