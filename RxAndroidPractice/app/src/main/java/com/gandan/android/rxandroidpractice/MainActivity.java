package com.gandan.android.rxandroidpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    //미리 키워드를 할당해보았다.
    String[] keyWord = {"spring", "springfield", "springton", "sparkle", "spark", "hello", "world", "eight", "friday", "church", "computer", "kotlin", "java", "android", "studio", "google", "milk", "money", "password"};
    List<String> purifiedWord = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        makeObservable();

        //RxBinding 라이브러리를 설치하면, RxTextView를 사용할 수 있다.
        Observable < CharSequence > etObservable = RxTextView.textChanges(editText);
        //MainTHread를 사용하거나, Looper를 통해 Observer를 지정할 수 있다.
        etObservable.subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            //데이터의 발행
            @Override
            public void onNext(CharSequence charSequence) {
                //editText에 글자를 입력하는대로 textView에 나타난다.
                textView.setText(charSequence);
                //입력한 값 중에서 일치하는 값 저장하기
                for(String string : keyWord){
                    //CharSequence를 String으로 변환해야 상호비교가 가능하다.
                    if(string.equals(String.valueOf(charSequence))){
                        purifiedWord.add(string);
                        Log.e("purifiedWord", purifiedWord.toString()+"");
                    }
                }
            }

            //에러 발생.
            @Override
            public void onError(Throwable e) {

            }

            //모든 데이터 발행 완료.
            @Override
            public void onComplete() {
                //EditText는 계속 입력을 받을 수 있기 때문에, onComplete에서 받는 것은 없다.
            }
        });

    }


    //RxJava의 기본인 Observable 생성

    private void makeObservable() {
        List<String> testArray = Arrays.asList("one", "two", "three", "four", "five");

        //Observable 생성.
        Observable<String> testObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("one");
                emitter.onNext("two");
                emitter.onNext("three");
                emitter.onNext("four");
                emitter.onNext("five");
                emitter.onComplete();
            }
        });
        testObservable.subscribe(this::printText);

        //Observable 생성 단순화
        Observable<String> simpleObservable = Observable.just("one", "two", "three", "four", "five");
        simpleObservable.subscribe(this::printText);

        //위에서 생성한 keyWord 전역변수 array 가져와 Observer 생성
        Observable<String> arrayObservable = Observable.fromArray(keyWord);
        arrayObservable.subscribe(this::printText);

        //위에서 생성한 testArray 가져와 Observer 생성
        Observable<String> iterableObservable = Observable.fromIterable(testArray);
        iterableObservable.subscribe(this::printText);

    }

    private void printText(String string){
        Log.e("testObservable", string+"");
    }
}
