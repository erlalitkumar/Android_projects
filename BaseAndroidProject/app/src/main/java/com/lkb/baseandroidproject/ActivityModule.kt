package com.lkb.baseandroidproject

import android.app.Activity
import dagger.Module

//@Module
abstract class ActivityModule{
    abstract fun injectActivity():Activity
}