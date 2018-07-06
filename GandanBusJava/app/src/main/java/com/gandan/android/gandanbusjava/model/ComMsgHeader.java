package com.gandan.android.gandanbusjava.model;

import org.simpleframework.xml.Element;

public class ComMsgHeader {

    //@Element(name = "returnCode")
    private String returnCode;

    //@Element(name = "errMsg")
    private String errMsg;

    public String getReturnCode ()
    {
        return returnCode;
    }

    public void setReturnCode (String returnCode)
    {
        this.returnCode = returnCode;
    }

    public String getErrMsg ()
    {
        return errMsg;
    }

    public void setErrMsg (String errMsg)
    {
        this.errMsg = errMsg;
    }


}
