# 버터나이프 배워보기

## 1. 버터나이프란?
- 안드로이드 내에서 다양한 View를 사용할 떄 변수를 선언하고, 그 변수에 맞춰 View들을 연결하는 작업을 해야함.
- 버터나이프는 findViewById()로 대표되는 이러한 연결작업의 단계를 줄여줌.
- 사용하기도 쉬워서 코드의 간결성과 재사용성 확보.

## 2. 버터나이프 설치
- Gradle(app)에 아래와 같이 입력한다.
```java
    dependencies{
        compile 'com.jakewharton:butterknife:8.8.1'
        annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    }
```

## 3. 버터나이프 사용
- 버터나이프로 바인딩해보기
    - 버터나이프는 Annotation을 적극적으로 활용한다.
     > 간혹 블로그나 사이트에서 Annotation 구문을 @BindView가 아닌 @InjectView나 @Bind로 알려주기도 하지만, 예전 버전에서 사용하던 구문으로 지금은 쓰이지 않으니 유의!

```java
    public class MainActivity extends AppCompatActivity {   
        
        /** 1) ButterKnife를 사용하기 위해 Annotation으로 BindView를 선언해준다.
        * (선언하면 Butterknife에서 자동으로 레이아웃에서 해당 id를 찾는다.)
        * 괄호 안에 뷰의 id를 넣어준다.
        * id에 바인딩할 데이터를 가진 타입과 변수를 선언한다.
        */

        @BindView(R.id.textView1) TextView textView1;
        @BindView(R.id.textView2) TextView textView2;
        @BindView(R.id.btnButton1) Button btnButton1;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**4) 버터나이프로 바인딩함을 선언해준다.
        * findViewById를 선언할 수 있는 곳이면, 어디든 .bind()를 사용할 수 있다.
        */

        ButterKnife.bind(this);

    }

 ```
- 바인딩 후 변경점 적용시키기
    
```java    
    //위에 코드와 이어짐.
    ButterKnife.bind(this);

    /* 1) ButterKnife에서도 findViewById로 선언한 것과 동일하게 하면 작동. */
    textView1.setText("Test Text");

    /* 2) Listener 적용하기
    * onClickListener를 선언하지 않고, @OnClick 어노테이션을 사용하는 것만으로도, 정상 작동.
    */
    @OnClick(R.id.btnButton1)
    public void btnChange(Button button){
        button.setText("Hello!");
        textView2.setText("Hello World!");
    }
```
    
## 4. 버터나이프도 귀찮다면?

### 1. [FindViewByMe](https://github.com/laobie/FindViewByMe)
- FindViewByMe는 안드로이드스튜디오와 같은 JetBrain IDE 한정 라이브러리
- View를 바인딩하기를 원하는 레이아웃 XML 이름에 마우스를 가져다 댄 후, 오른쪽 클릭을 하고 메뉴에서 findViewByMe를 선택하기만 하면 된다.

