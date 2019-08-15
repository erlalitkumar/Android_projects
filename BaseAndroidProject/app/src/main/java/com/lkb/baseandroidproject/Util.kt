package com.lkb.baseandroidproject

    fun blankSpaceForMarquee(): String {
        var space = " "
        repeat((1..50).count()) { space += " " }
        return space
    }
