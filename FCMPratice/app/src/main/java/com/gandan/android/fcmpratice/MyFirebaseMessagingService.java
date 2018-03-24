package com.gandan.android.fcmpratice;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by XPS on 2018-03-22.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    String message;

    NotificationChannel notificationChannel;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        message = remoteMessage.getNotification().getBody();
        Log.e("messageReceived", message+"");

        if(Build.VERSION.SDK_INT >= 26){
            Log.e("test", message+"");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationChannel = new NotificationChannel(getString(R.string.fcm), "notiName", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("notification Test");
            notificationManager.createNotificationChannel(notificationChannel);
            Notification.Builder notiBuilder = new Notification.Builder(this, getString(R.string.fcm));
            notiBuilder.setContentText(message).setSmallIcon(R.drawable.ic_launcher_foreground).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background)).setChannelId(getString(R.string.fcm)).build();
        } else {

        }
    }

}
