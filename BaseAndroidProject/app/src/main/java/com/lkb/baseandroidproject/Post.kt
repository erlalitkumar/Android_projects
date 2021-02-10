package com.lkb.baseandroidproject

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(val title: String)