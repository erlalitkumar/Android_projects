package com.lkb.baseandroidproject

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: androidx.recyclerview.widget.RecyclerView, items: List<T>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(items)

    }
}


@BindingAdapter("changedPositions")
fun <T> setDataChanged(recyclerView: androidx.recyclerview.widget.RecyclerView, positions: Set<Int>) {
    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).changedPositions(positions)

    }

}