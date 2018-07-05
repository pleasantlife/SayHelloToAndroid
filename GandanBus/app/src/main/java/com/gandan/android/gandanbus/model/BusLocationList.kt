package com.gandan.android.gandanbus.model

import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "busLocationList")
data class BusLocationList(var endBus : Int, var plateType : Int, var remainSeatCnt : Int, var stationSeq : Int, var stationId : Long, var routeId : Long, var lowPlate : Int, var plateNo : String) {
}