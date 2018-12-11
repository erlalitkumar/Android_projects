package com.lkb.baseandroidproject

object WikiModel {
    data class Result(val query:Query)
    data class Query(val searchinfo: SearchInfo)
    data class SearchInfo(val totalhits: Int)
}