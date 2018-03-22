package com.gandan.android.fcmpratice;


import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by XPS on 2018-03-22.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String message;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        message = remoteMessage.getNotification().getBody();
        Log.e("messageReceived", message+"");
    }

}
