package com.lkb.base.model

interface IUser{
    fun getEmail():String
    fun getPassword():String
    fun isValidData():Int
}