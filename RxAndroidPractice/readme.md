# RxAndroid 연습해보기

## 1. Rx란?
 - Rx는 ReactiveX의 줄임말로서, 관찰자인 '옵저버블'을 통해 비동기(asynchronous) 데이터 스트림을 처리하는 라이브러리이다.
 - Java는 물론, Android, Swift, JavaScript 등 다양한 언어와 플랫폼에서 Rx를 사용할 수 있다.

## 2. RxAndroid를 위해 반드시 알아야 하는 개념들
1) Observable

## 2. RxAndroid 설치하기
 - ReactiveX는 Android용이 따로 존재한다.
 - App gradle에 아래와 같이 바이너리를 추가해준다.
 <pre><code>
 dependencies{
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.8'
 }
 </code></pre>

## 3. 리액티브 앱 만들어보기
 - [Realm Academy의 게시물](https://academy.realm.io/kr/posts/rxandroid/) 등을 참조하여, 리액티브 앱을 만들어보고자 한다.
 - 참고사이트 1 : https://www.slideshare.net/StellaKim9/rxandroid-71638745
 - 참고사이트 2 : https://medium.com/@31536k_/rxandroid-tutorial-raywenderlich-d949ac277f21
  
