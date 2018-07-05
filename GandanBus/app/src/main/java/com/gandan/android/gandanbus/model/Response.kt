package com.gandan.android.gandanbus.model

import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "response")
data class Response(var msgHeader : MsgHeader, var msgBody : MsgBody, var comMsgHeader : String) {
}