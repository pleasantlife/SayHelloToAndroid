package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import java.util.ArrayList

@Root (name = "response")
data class MsgBody(@field:ElementList(name = "busLocationList", type = BusLocationList::class, required = false, entry = "busLocationList", inline = true) var busLocationList: List<BusLocationList>) {
    constructor() : this(mutableListOf<BusLocationList>())
}