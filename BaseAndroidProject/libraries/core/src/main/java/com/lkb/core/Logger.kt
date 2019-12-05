package com.lkb.core

import android.util.Log

fun logger() {
    Log.d("Logger", "Hello form logger")
}

fun debugLogger(tag: String, msg: String) {
    Log.d(tag, msg)
}