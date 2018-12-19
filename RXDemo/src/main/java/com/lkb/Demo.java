package com.lkb;

import io.reactivex.Flowable;

public class Demo {
    public static void main(String[] args) {
       print1to5();
    }
    public static void print1to5(){
        Flowable.range(1,5)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}

