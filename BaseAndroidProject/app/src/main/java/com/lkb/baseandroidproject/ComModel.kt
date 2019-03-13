package com.lkb.baseandroidproject

data class ComModel (
   var cmd:String = "",// command to execute corresponding service
   var cLat:Float=0.0f,// current latitude
   var cLong:Float=0.0f,// current longitude
   var cTime:Long=0,// location last update time
   var bats:Int=0// battery status
)