# Firebase Cloud Message (FCM)

# FCM?
- 구글에서 아이폰/안드로이드로 푸쉬를 보낼 수 있는 서비스
- Firebase 서비스 시작 전에는 Google Cloud Message(GCM)이 있었으나, 현재는 FCM으로 일원화.
- 별도의 인증이 필요없으며, 앱의 실행상태에 관계없이 노티를 보낼 수 있어 많이 이용하고 있음.

# 안드로이드 알림채널
- API 버전 26부터 '알림 채널'(Notification Channel)이라는 개념이 생김.
- 알림채널을 설정하지 않으면 노티가 뜨지 않음.
- 알림채널을 통해 노티피케이션의 중요도 설정 가능.
- NotificationChannel 클래스를 통해 설정 가능.
```java
NotificationChannel notificationChannel = new NotificationCHannel(fcmid, name, importance);
```

# 유의사항
- 아이콘에 색이 들어가있어도 흰색과 투명으로만 처리된다. (구글 머티리얼 디자인 가이드)
- 일반 레이아웃에서 사용하는 dpi의 기준이 다르다.
- dpi 기준
 1) mdpi : 24px x 24px
 2) hdpi : 36px x 36px
 3) xhdpi : 48px x 48px
 4) xxhdpi : 72px x 72px
 5) xxxhdpi : 96px x 96px
- 위의 dpi 기준이 지켜지지 않으면, 'Can't Create ICON오류를 내면서 앱이 실행되지 않을 수 있다.
 