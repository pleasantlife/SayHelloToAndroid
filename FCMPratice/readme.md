# Firebase Cloud Message (FCM)

## 1. FCM?
- 구글에서 아이폰/안드로이드로 푸쉬를 보낼 수 있는 서비스
- Firebase 서비스 시작 전에는 Google Cloud Message(GCM)이 있었으나, 현재는 FCM으로 일원화.
- 별도의 인증이 필요없으며, 앱의 실행상태에 관계없이 노티를 보낼 수 있어 많이 이용하고 있음.

## 2. 안드로이드 알림채널
- API 버전 26부터 '알림 채널'(Notification Channel)이라는 개념이 생김.
- 알림채널을 설정하지 않으면 노티가 뜨지 않음. (아래의 예제에서는 'fcmid')
- 알림채널을 통해 노티피케이션의 중요도 설정 가능.
- NotificationChannel 클래스를 통해 설정 가능.
```java

//NotificationChannel 생성 1
NotificationChannel notificationChannel = new NotificationChannel(fcmid, name, importance);
//NotificationChannel 생성 2
NotificationManager notiManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
NotificationChannel notiChannel = notimanager.getNotificationChannel(fcmid);
```

## 3. 유의사항
- 아이콘에 색이 들어가있어도 흰색과 투명으로만 처리된다. (구글 머티리얼 디자인 가이드)
- 일반 레이아웃에서 사용하는 dpi의 기준이 다르다.
- dpi 기준
 1) mdpi : 24px x 24px
 2) hdpi : 36px x 36px
 3) xhdpi : 48px x 48px
 4) xxhdpi : 72px x 72px
 5) xxxhdpi : 96px x 96px
- 위의 dpi 기준이 지켜지지 않으면, Couldn't create 

## 4. FCM 사용해보기(안드로이드!)

### 1. 데이터 수신하기
1) 라이브러리 설치

 - 안드로이드 스튜디오에서 연결하지 않을 경우, google-services.json 파일을 다운로드 받아서 프로젝트 폴더 내에 넣어주어야 함.
 - 먼저 안드로이드 그래들에 Firebase Core와 Messaging 라이브러리 등록
```java
 //app gradle
dependencies {
    //안드로이드 스튜디오의 Firebase 연결메뉴를 이용하지 않으면, 아래의 core 라이브러리도 가져와야 함.
    implementation 'com.google.firebase:firebase-core:11.8.0'
    implementation 'com.google.firebase:firebase-messaging:11.6.2'
}
```

2) 서비스 생성하기
 - FCM 수신을 위해서는 FirebaseInstanceIdService와 FirebaseMessagingService를 상속받은 서비스를 생성해야한다.
 - FirebaseInstanceIdService는 현재 접속한 기기의 토큰을 받을 수 있다.
 - FirebaseMessagingService는 현재 접속한 기기가 메세지를 수신했을 때 할 행동을 지정해주는 클래스이다.
 - 안드로이드에서 액티비티를 생성하듯 서비스를 생성한다.
 - 생성 후, 상속받는 클래스를 변경한다.
```java

//토큰을 받기 위한 FirebaseInstanceIdService 상속 클래스
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    //여기에서 토큰이 발급될 때 마다 확인할 수 있다.
    @Override
    public void onTokenRefresh(){
        String token = FirebaseInstanceId.getInstance().getToken();
    }
}

//메세지를 수신받을 FirebaseMessagingService 상속 클래스
public class MyFirebaseMessagingService extneds FirebaseMessagingService {

    @Override
    public void onMessageReceived(){
        //TODO : 노티 설정
    }
}
```
 - 서비스가 생성되면 안드로이드 매니페스트를 아래와 같이 수정해야 한다. 
```xml
<application
    android:label="AppLabel"
    android:theme="@style/AppTheme">
    <activity android:name".MainActivity"/>
    <!-- 수정하기 전 -->
    <service
        android:name=".MyFirebaseInstanceIDService"/>
    <service
        android:name=".MyFirebaseMessagingService"/>

    <!-- 수정하고 난 후-->
    <service 
        android:name=".MyFirebaseInstanceIDService">
        <intent-filter>
            <action android:name="com.google.firebase.INSTANCE_ID_EVENT">
        </intent-filter>
    </service>
    <service
        android:name=".MyFirebaseMessagingService">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT">
        </intent-filter>
    </service>            
</application>        
``` 

3) 메세지 받기
- FCM을 통해 전송되는 메세지는 FirebaseMessagingService를 상속받은 서비스 내의 onMessageReceived() 메소드에서 받을 수 있따.
```java
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        String message = remoteMessage.getNotification().getBody();

        //TODO : NOTIFICATION
    }
}
```