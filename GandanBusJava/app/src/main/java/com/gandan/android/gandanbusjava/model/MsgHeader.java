package com.gandan.android.gandanbusjava.model;

import org.simpleframework.xml.Element;

public class MsgHeader {

    @Element(name = "resultCode")
    private String resultCode;

    @Element(name = "queryTime")
    private String queryTime;

    @Element(name = "resultMessage")
    private String resultMessage;

    public String getResultCode ()
    {
        return resultCode;
    }

    public void setResultCode (String resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getQueryTime ()
    {
        return queryTime;
    }

    public void setQueryTime (String queryTime)
    {
        this.queryTime = queryTime;
    }

    public String getResultMessage ()
    {
        return resultMessage;
    }

    public void setResultMessage (String resultMessage)
    {
        this.resultMessage = resultMessage;
    }

}
