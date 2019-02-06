package com.lkb.baseandroidproject

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "first_name") var firstName: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "email") var email: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0 // or uid:Int?=null
}