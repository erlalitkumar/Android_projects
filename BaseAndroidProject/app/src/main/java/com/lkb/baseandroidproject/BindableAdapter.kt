package com.lkb.baseandroidproject

interface BindableAdapter<T> {
    fun setData(items:List<T>)
    fun changedPositions(positions:Set<Int>)
}