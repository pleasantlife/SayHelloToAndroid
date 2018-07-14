package com.gandan.android.gandanbus.model

import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root
class BusLocationList {
    @Path("busLocationList")
    @field:Element(name = "endBus") var endBus : Int? = 0

    @field:Element(name = "plateType") var plateType : Int? = 0

    @field:Element(name = "remainSeatCnt") var remainSeatCnd : Int? = 0

    @field:Element(name = "stationSeq") var stationSeq : Int? = 0

    @field:Element(name = "stationId") var stationId : Long? = 0

    @field:Element(name = "routeId") var routeId : Long? = 0

    @field:Element(name = "lowPlate") var lowPlate : Int? = 0

    @field:Element(name = "plateNo") var plateNo : String? = null
}