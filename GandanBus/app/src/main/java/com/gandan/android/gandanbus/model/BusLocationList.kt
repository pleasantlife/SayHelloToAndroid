package com.gandan.android.gandanbus.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.simpleframework.xml.Element


data class BusLocationList(var endBus : Int, var plateType : Int, var remainSeatCnt : Int, var stationSeq : Int, @PropertyElement var stationId : Long, @PropertyElement var routeId : Long, @PropertyElement var lowPlate : Int, @PropertyElement var plateNo : String) {
}