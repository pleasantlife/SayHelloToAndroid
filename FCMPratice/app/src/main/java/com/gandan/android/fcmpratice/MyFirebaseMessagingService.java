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

        //Oreo 버전부터는 노티를 받을 떄 알림채널을 설정해줘야 한다.
        if(Build.VERSION.SDK_INT >= 27){
            int id = 3;
            Log.e("test", message+"");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationChannel = notificationManager.getNotificationChannel(getString(R.string.fcm));
            notificationChannel.setDescription("notification Test");
            notificationManager.createNotificationChannel(notificationChannel);
            Notification notiBuilder = new Notification.Builder(this).setContentTitle("Testing...").setContentText(message).setSmallIcon(R.drawable.ic_launcher_foreground).setChannelId(getString(R.string.fcm)).build();
            //아래의 int id는 정하기 나름~!
            //파이어베이스 콘솔에서 보내는 알림채널 id와 클라이언트 앱에서 받는 id가 같아야 온다! => 선택사항이라고 안쓰면 안된다.
            notificationManager.notify(3, notiBuilder);

        }
        //26버전 이하면 적용되지 않음.
        else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new Notification.Builder(this).setContentTitle("Testing Under 27").setContentText(message).setSmallIcon(R.drawable.ic_launcher_foreground).build();
            notificationManager.notify("tag", 3, notification);
        }
    }

}
