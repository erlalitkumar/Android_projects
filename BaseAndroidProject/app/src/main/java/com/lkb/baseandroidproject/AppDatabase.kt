package com.lkb.baseandroidproject

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [User::class], version = 1)//arrayOf(User::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
}