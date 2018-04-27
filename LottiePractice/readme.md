# 로티(Lottie) 사용해보기

## 로티란?
 - Airbnb에서 제공하는 모바일 애니메이션 라이브러리
 - iOS, Android, 웹에서 간편하게 Adobe After Effects를 통해 제작한 애니메이션을 재생시킬 수 있다.
 - 단, 재생을 위해 After Effects 파일을 Bodymovin 라이브러리를 통해 json으로 변환해야 한다.
 - 로티를 사용하면 앱 내의 json 애니메이션 파일은 물론, 네트워크를 통해 웹에서 받아온 json 애니메이션 파일도 바로 사용 가능하다.
 - 소개 페이지 : [http://airbnb.io/lottie/](http://airbnb.io/lottie/)

## 로티 사용법 in 안드로이드
 - [로티 라이브러리용 json 파일 모음 사이트](https://www.lottiefiles.com)에 접속하여 원하는 애니메이션 json 파일을 다운받는다.
 - 단, 다운받은 json 파일은 안드로이드 프로젝트 내의 assets 폴더에 저장해야한다.
 - 그래들에 로티 라이브러리를 추가한다.
 - 로티 라이브러리 추가시 minSdk를 15버전으로 맞추고 있다면, lottie:2.2.0 버전을 사용해야 한다. 이 이상의 버전을 사용하면, minSdk를 16으로 맞춰야 한다.
 - xml에 LottieAnimationView(com.airbnb.lottie.LottieAnimationView)를 추가시킨다.
 - 추가시킨 LottieAnimationView에 app:lottie_fileName 속성에서 다운로드 받은 json 파일명을 입력해준다.
