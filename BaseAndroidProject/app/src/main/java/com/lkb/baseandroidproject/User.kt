package com.lkb.baseandroidproject

import android.databinding.BaseObservable
import android.databinding.Bindable

class User : BaseObservable() {
    @get:Bindable
    var name:String=""
     set(value){
        field =value
        notifyPropertyChanged(BR.name)
    }

    @get:Bindable
    var email:String=""
         set(value){
            field =value
            notifyPropertyChanged(BR.email)
        }
}