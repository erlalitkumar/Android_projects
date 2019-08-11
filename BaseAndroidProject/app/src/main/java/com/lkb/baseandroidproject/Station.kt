package com.lkb.baseandroidproject

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Station(
        val title: String,
        val url: String
) {
    var isPlaying = false

}