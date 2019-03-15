package com.lkb.baseandroidproject.model

data class ComModel (
   var cmd:String = "",// command to execute corresponding service
   var cLat: Double =0.0,// current latitude
   var cLong: Double =0.0,// current longitude
   var cTime:Long=0,// location last update time
   var bats:Int=0// battery status
)