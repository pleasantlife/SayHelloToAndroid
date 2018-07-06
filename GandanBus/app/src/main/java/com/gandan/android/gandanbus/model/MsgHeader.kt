package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Element(name = "msgHeader")
class MsgHeader(@Element(name = "resultCode") var resultCode : String, @Element(name = "queryTime") var queryTime : String, @Element(name = "resultMessage") var resultMessage : String) {
}