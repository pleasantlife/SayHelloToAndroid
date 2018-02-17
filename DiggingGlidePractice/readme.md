# Glide 라이브러리 파먹기

## 1. Glide 라이브러리 소개
- Smooth scrolling에 초점을 맞추어 빠르고 효율적으로 이미지를 로딩할 수 있도록 해주는 안드로이드 라이브러리.
- HttpUrlConnection을 베이스로 하지만 Google의 Volley와 Square의 Okhttp 라이브러리도 활용하고 있다.
- 소개 사이트 : https://bumptech.github.io/glide/

## 2. Glide 라이브러리 적용
- app gradle 내의 dependencies에 아래와 같이 적용해준다.
```java
dependencies {
  implementation "com.github.bumptech.glide:glide:4.6.1"
```
- 단, Glide가 지원하는 Support Library Version은 27이기 때문에, 만약 27 이하의 Support Library를 사용한다면 아래와 같이 적용해야한다.
```java
dependencies {
  implementation ("com.github.bumptech.glide:glide:4.6.1") {
    exclude group: "com.android.support"
  }
  implementation "com.android.support:support-fragment:26.1.0"
}
```
- 위와 같이 적용하지 않은 채로 27 이하의 Support Library 사용시, 앱을 실행하면 바로 아래의 에러가 나온다.
```java
java.lang.NoSuchMethodError: No static method getFont(Landroid/content/Context;ILandroid/util/TypedValue;ILandroid/widget/TextView;)Landroid/graphics/Typeface; in class Landroid/support/v4/content/res/ResourcesCompat; or its super classes (declaration of 'android.support.v4.content.res.ResourcesCompat' at android.support.v7.widget.TintTypedArray.getFont(TintTypedArray.java:119)
```
### 2-1. AndroidManifest를 통한 권한 부여
- Glide 라이브러리를 사용한다면, AndroidManifest.xml에서 인터넷 접속 권한을 추가해 줘야 한다.
- 또한 비행기 모드 등의 상태 관리를 위해 ACCESS_NETWORK_STATE 권한도 함께 추가할 것을 권장한다.(필수 사항은 아님!)
- 로컬 메모리 또는 외장 메모리에서 이미지를 가져올 예정이라면, READ_EXTERNAL_STORAGE 권한도 추가해야 한다.


## 3. Glide 사용

## 4. RecyclerView와의 통합

### 4-1. Gradle 추가
- RecyclerView와 Glide 간의 통합을 위해 app gradle에 아래와 같이 추가해준다.
```java
compile ("com.github.bumptech.glide:recyclerview-integration:4.6.1") {
  // Excludes the support library because it's already included by Glide.
  transitive = false
}
```
- 물론, 리사이클러뷰 라이브러리도 추가해야한다.

### 4-2. PreloadSizeProvider 생성하기
### 4-3. PreloadModelProvider 생성하기
### 4-4. PreloadSizeProvider와 PreloadModelProvider를 결합한 RecyclerViewPreloader 생성하기
### 4-5. RecyclerView Scroll Listener에 RecyclerViewPreloader 결합하기

