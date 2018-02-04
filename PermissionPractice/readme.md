# 사용자로부터 필요한 권한(Permission) 요청하기

## 1. 권한 요청이 필요한 이유
 - 안드로이드 6.0(API 레벨 23) 이전까지는 권한 요청에 대해 관대하여 Android Manifest에 권한요청코드를 넣기만 해도 설치시에 권한을 얻을 수 있었다.
 - 하지만, 무분별한 권한 부여로 인해 사용자 기밀데이터에 대한 접근이 쉬워지게 되었다.
 - 따라서, 이를 막기 위해 앱이 설치될 떄에는 권한 부여에 대한 안내를 하되, 실제로 권한을 요청하는 것은 앱이 실행될 떄로 한정지었다.
 - 또한, 사용자의 동의를 설치시에 받는 것이 아니라 사용시에 받는 것으로 변경하여 더욱 명시적인 권한 요청이 가능해졌다.
 - 관련 설명 : https://developer.android.com/training/permissions/requesting.html?hl=ko

## 2. 정상 권한 및 위험 권한
 - 정상권한 : 사용자 개인 정보나 다른 앱의 작업에 위험이 거의 없는 부분. (예: 표준 시간대 설정)
 - 위험권한 : 사용자의 개인 정보를 포함하거나 사용자가 저장한 데이터, 또는 다른 앱의 작업에 영향을 미칠 수 있는 데이터나 리소스를 요청하는 권한 (예: 메모리 쓰기 등)
 - 권한 그룹 관련 설명 : https://developer.android.com/guide/topics/security/permissions.html?hl=ko#normal-dangerous

## 3. 권한을 취득하는 과정
 1) checkSelfPermission()메서드를 호출하여 앱의 기능에 필요한 권한을 보유하였는지 확인.
 2-1) 앱에 권한이 있는 경우 PackageManager.PERMISSION_GRANTED를 반환
 2-2) 앱에 권한이 없는 경우 PackageManager.PERMISSION_DENIED를 반환하며, 명시적으로 권한을 요청해야 함.
 3) 앱에 권한이 없는 경우 requestPermissions() 메서드 중 하나를 호출하여 적절한 권한을 요청함.
 3-1) requestPermissions() 메서드는 비동기식으로 작동하여, 사용자가 대화상자에 응답한 결과를 전달한다.
 3-2) onRequestPermissionsResult()를 통해 시스템은 퍼미션에 대한 응답결과를 알 수 있다. 
 4) 한 번 취득한 권한을 별도로 취득할 필요는 없다.
 4-1) 단, 사용자가 향후에 권한 취득을 취소할 수 있도록 할 필요가 있다.
