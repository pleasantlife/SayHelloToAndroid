# 안드로이드에서 Retrofit 사용하기

# 1. Retrofit이란?
 - HTTP API를 자바 인터페이스 형태로 사용할 수 있도록 만들어 놓은 라이브러리
 - Java와 Android에서 사용할 수 있는 라이브러리
 - 이러한 라이브러리는 Retrofit 뿐만 아니라 Volley 등 여러가지가 있음.
 - [Android의 HTTP 클라이언트 라이브러리(2013년판)](http://d2.naver.com/helloworld/377316)
 

## 1) Retrofit을 쓰는 이유?
 - HTTP 통신에 필요한 C(create : POST), R(read : GET), U(update : PUT), D(delete)기능을 모두 제공함.
 - AsyncTask를 활용한 HttpUrlConnection은 긴 코드, 잦은 통신 오류, 재사용성의 어려움 등으로 많은 시간과 노력을 필요로 함.
 - 다른 라이브러리에 비해서도 더 빠른 성능을 보여줌.
 - JSON이나 XML 방식의 데이터로 입/출력을 지원함.(Gson, Simple XML 등을 사용)
 - Java 1.7 또는 안드로이드 2.3 이상이면 작동하여 범용성이 높음.

## 2) CRUD란?
 - 기본적인 데이터 처리 기능인 Create(생성), Read(읽기), Update(갱신), Delete(삭제)를 묶어서 일컫는 말이다. [(출처 : 위키백과)](https://ko.wikipedia.org/wiki/CRUD)
 - 데이터베이스 또는 Restful API에서 자주 이야기하는 개념
 - HTTP 통신에선 Update의 개념에 PATCH, PUT 등이 함께 들어가있기도 하다.

# 2. Retrofit 설치하기

 - http://square.github.io/retrofit/ 으로 접속하여 Download 란에서 GRADLE 부분의 구문을 복사한다. 
 - 안드로이드 스튜디오에서 생성한 프로젝트 내의 build.gradle(Module:app) 파일로 들어간다.
 - 파일 내의 dependencies 부분에 implementation 구문을 적는다.
 - Sync Now 또는 Rebuild Project를 하면 적용완료!

# 3. Retrofit 사용하기

## 1) 데이터를 저장할 Class 생성
## 2) Retrofit 선언
- 원하는 액티비티나 클래스에서 아래와 같이 선언하면 된다.

```java
    //Json 데이터를 받아서 처리할 것이기 때문에, 
    //GsonConverterFactory를 사용한다.
    //(GsonConverterFactory는 별도로 그래들에서 implementation을 수행해야 한다.)   
    Retrofit retrofitBuilder = new Retrofit.Builder()
                                .baseUrl("http://jsonplaceholder.typicode.com/")
                                .addConverterFactory(GsonConverterFactor.create())
                                .build();
```

## 3) Retrofit과 연결할 Interface 생성 및 CRUD에 따른 로직 등록    
## 4) Interface와 연결
## 5) CRUD 수행

# 4. okHttp와 결합하여 사용하기
 - okHttp를 결합하면, 서버와 통신하는 상황에 대한 제어는 물론, Interceptor를 통해 통신 로그를 확인할 수도 있다.


# 5. RxAndroid와 결합하여 사용하기
 - Retrofit2에는 기본적으로 RxJava2Adapter가 있어서 Call 대신 Observable과 Flowable로 데이터를 받을 수 있다.
 
## 1) 그래들 설정하기
 - App gradle에 아래 세 라이브러리를 추가한다.

```java
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
    implementation 'io.reactivex.rxjava2:rxjava:2.1.9'
```

## 2) Retrofit에 어댑터 추가
 - addCallAdapterFactory(RxJava2CallAdapterFactory.create())를 추가한다.

```java
Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl("SERVER_URL").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConveterFactory.create()).build();
```

## 3) Interface에 선언하기
 - Rx를 사용하지 않는 경우 Call<T>를 사용했으나, Rx는 Observable<T> / Flowable<T>를 사용할 수 있다.

```java 
    @GET("posts/{id}")
    Observable<PostInfo> getPost(@Path ("id") int id);
```

## 4) Retrofit 선언 후 사용하기


```java
Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl("SERVER_URL")
.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
.addConverterFactory(GsonConveterFactory.create())
.build();

PostService postService = retrofitBuilder.create(PostService.class);

Observable<PostInfo> post = postService.getPost(1);
post.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.MainThread()).observe(new Observble<PostInfo>(){
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(PortfolioForDetail portfolioForDetail) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
});
```

## 5) 기타 팁!
- Retrofit에서 로그아웃 등 Response를 받지 않아도 되는 작업을 수행하고자 한다면, Void Type을 사용할 수 있다.
```java

Retrofit retrofitBuilder = new Retrofit.Builder().baseUrl("SERVER_URL")
.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
.addConverterFactory(GsonConveterFactory.create())
.build();

Call<Void> voidResponse = retrofitBuilder.doLogout();
voidResponse.enqueue(new Callback<Void>() {
   
    @Override
    public void onResponse(Call<Void> call, Response<Void> response){
        //연결이 성공했을 때 할 일을 지정
    }
    @Override
    public void onFailure(Call<Void> call, Throwable t){
        //연결이 실패했을 때 할 일을 지정
    }

});

```
- Retrofit에서 Multipart Annotation을 사용하면, text이외에 파일도 POST 할 수 있으나 한글을 POST할 경우 앞 뒤로 따옴표가 붙는다.
- 따라서, RequestBody 객체를 통해 plain text임을 명시적으로 밝혀야 한다.

```java
String titleString = "한글";
RequestBody titlePlain = RequestBody.create(MediaType.parse("text/plain"), titleString);
```

