package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(strict = false, name = "response")
class Response {

    /*@set:Element(name = "comMsgHeader")
    @get:Element var comMsgHeader : ComMsgHeader? = null*/

    @field:Element(name = "comMsgHeader") var comMsgHeader : ComMsgHeader? = null

    @field:Element(name = "msgBody") var msgBody: MsgBody? = null

    @field:Element(name = "msgHeader") var msgHeader: MsgHeader? = null
}