package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


class MsgHeader {

    @field:Element(name = "resultCode") var resultCode : String? = null

    @field:Element(name = "queryTime") var queryTime : String? = null

    @field:Element(name = "resultMessage") var resultMessage : String? = null
}