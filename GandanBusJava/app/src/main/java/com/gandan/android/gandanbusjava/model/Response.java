package com.gandan.android.gandanbusjava.model;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class Response {

    @Element(name = "msgHeader")
    private MsgHeader msgHeader;

    @Element(name = "msgBody")
    private MsgBody msgBody;

    @Element(name = "comMsgHeader")
    private ComMsgHeader comMsgHeader;

    public MsgHeader getMsgHeader ()
    {
        return msgHeader;
    }

    public void setMsgHeader (MsgHeader msgHeader)
    {
        this.msgHeader = msgHeader;
    }

    public MsgBody getMsgBody ()
    {
        return msgBody;
    }

    public void setMsgBody (MsgBody msgBody)
    {
        this.msgBody = msgBody;
    }

    public ComMsgHeader getComMsgHeader ()
    {
        return comMsgHeader;
    }

    public void setComMsgHeader (ComMsgHeader comMsgHeader)
    {
        this.comMsgHeader = comMsgHeader;
    }

}
