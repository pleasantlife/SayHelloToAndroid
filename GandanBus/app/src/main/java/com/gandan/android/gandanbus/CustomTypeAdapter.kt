package com.gandan.android.gandanbus

import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

open class CustomTypeAdapter : TypeAdapter<String>{
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): String {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return String()
    }

    override fun toXml(writer: XmlWriter?, config: TikXmlConfig?, value: String?, overridingXmlElementTagName: String?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}