package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element


class ComMsgHeader {

    @field:Element(name = "returnCode") var returnCode : String? = null

    @field:Element(name = "errMsg") var errMsg : String? = null

}