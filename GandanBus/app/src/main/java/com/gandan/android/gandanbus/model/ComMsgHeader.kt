package com.gandan.android.gandanbus.model

import org.simpleframework.xml.Element

data class ComMsgHeader(@Element var returnCode : String, @Element var errMsg : String) {
}