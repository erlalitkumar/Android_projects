package com.lkb.baseandroidproject

import android.databinding.BaseObservable
import android.databinding.ObservableField

class User : BaseObservable() {
    var name:ObservableField<String> = ObservableField()
    var email:ObservableField<String> = ObservableField()

}