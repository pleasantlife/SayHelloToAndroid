# 안드로이드에서 쓸 수 있는 아주아주아주 사소한 팁들

## 1. 원형배경 넣기
 - TextView를 사용할 때 원형 배경을 넣고 싶을 때가 있다. 그럴 때, 사용하면 좋은 방법.
 <pre><code>
 @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  원형배경 넣기
         */
        //1) TextView 선언
        TextView textViewCircleBorder = findViewById(R.id.textViewCircleBorder);
        //2) Layout Resource File(.xml)만들기 (여기서는 circle_background.xml로 생성)
        //3) TextView에 circle_background를 background로 등록 (activity_main.xml에서도 등록 할 수 있음)
        textViewCircleBorder.setBackgroundResource(R.drawable.circle_background);
        //4) 글자가 원의 중앙에 가도록 gravity 설정
        //5) 글자 색상 변경 (여기서는 흰색으로 변경)
        textViewCircleBorder.setTextColor(Color.WHITE);
      }
  </code></pre>

## 2. build.gradle에 라이브러리를 추가할 때, 충돌 해결하기
  - 쓰고 싶은 라이브러리가 있어서 build.gradle을 통해 추가하려는데 문제가 생길때가 있다.
  - 그럴때는 아래의 코드처럼 'exclude'를 활용한다.

  <pre><code>
  dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation ("com.github.bumptech.glide:glide:4.5.0") {
      exclude group: "com.android.support"
      }
    }
  </code></pre>
