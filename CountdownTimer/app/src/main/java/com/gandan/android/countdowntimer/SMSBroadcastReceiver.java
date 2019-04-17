package com.gandan.android.countdowntimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.e("OnReceive", "Function Call");

        Bundle data = intent.getExtras();
        if(data != null) {
            SmsMessage[] messages = parseSmsMessage(data);
            if (messages.length > 0) {
                String sender = messages[0].getOriginatingAddress();
                String contents = messages[0].getMessageBody();
                Date receivedDate = new Date(messages[0].getTimestampMillis());

                Log.e("Contents", contents + "");
            }
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0; i<objs.length; i++) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
            } else {
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }

        return messages;
    }


}
