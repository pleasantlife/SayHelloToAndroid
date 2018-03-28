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
    String title, message;
    NotificationChannel notificationChannel;
    int id = 3;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //message = remoteMessage.getNotification().getBody();
        if(remoteMessage.getData().size() > 0){
            Log.e("remoteData", remoteMessage.getData()+"");
            Log.e("test", remoteMessage.getData().get("hello")+"");
            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");
        } else {
            title = remoteMessage.getNotification().getTitle();
            message = remoteMessage.getNotification().getBody();
        }
        Log.e("messageReceived", message+"");

        //Oreo 버전부터는 노티를 받을 떄 알림채널을 설정해줘야 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Log.e("test", message+"");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationChannel = new NotificationChannel(getString(R.string.fcm), "Tested", NotificationManager.IMPORTANCE_DEFAULT);
            //notificationChannel = notificationManager.getNotificationChannel(getString(R.string.fcm));
            notificationChannel.setDescription("notification Test");
            notificationManager.createNotificationChannel(notificationChannel);
            //Notification notiBuilder = new Notification.Builder(this, getString(R.string.fcm)).setContentTitle("Testing...").setContentText(message).setSmallIcon(R.mipmap.ic_error_white_24dp).setChannelId(getString(R.string.fcm)).build();
            NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, getString(R.string.fcm)).setContentTitle(title).setContentText(message).setSmallIcon(R.mipmap.ic_error_white_24dp);
            //아래의 int id는 정하기 나름~!
            //파이어베이스 콘솔에서 보내는 알림채널 id와 클라이언트 앱에서 받는 id가 같아야 온다! => 선택사항이라고 안쓰면 안된다.
            notificationManager.notify(id, notificationCompat.build());
            onDeletedMessages();

        }
        //26버전 이하면 알림채널을 설정하지 않은 노티를 만들어줘도 잘 받음.
        else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this, getString(R.string.fcm)).setContentTitle(title).setContentText(message).setSmallIcon(R.mipmap.ic_error_white_24dp);
            notificationManager.notify(id, notificationCompat.build());
            onDeletedMessages();
        }
    }

}
