package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Root

@Root(name = "response")
class Response(var comMsgHeader: ComMsgHeader, var msgBody: MsgBody, var msgHeader: MsgHeader) {
}