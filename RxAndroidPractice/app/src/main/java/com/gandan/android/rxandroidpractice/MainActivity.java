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

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;


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
        //subscribe함수로 호출해야 Observable이 발행된다.(hot observable이기 때문!)
        testObservable.subscribe(this::printText);

        //Observable 생성 단순화 (단, just를 쓸 때의 아이템 타입은 모두 같아야 한다.)
        Observable<String> simpleObservable = Observable.just("one", "two", "three", "four", "five");
        simpleObservable.subscribe(this::printText);

        //위에서 생성한 keyWord 전역변수 array 가져와 Observer 생성
        Observable<String> arrayObservable = Observable.fromArray(keyWord);
        arrayObservable.subscribe(this::printText);

        //map()을 통해 원하는 값으로 변경할 수도 있다!
        Observable<String> mapObservable = Observable.fromArray(keyWord).map(s -> s+" stamp : "+System.currentTimeMillis());
        mapObservable.subscribe(this::printText);

        //위에서 생성한 testArray 가져와 Observer 생성
        Observable<String> iterableObservable = Observable.fromIterable(testArray);
        iterableObservable.subscribe(this::printText);
        

        //Observable을 Flowable로 바꿀 수도 있다.(반대로 바꾸는 경우도 가능하다.)
        Flowable<String> iterableFlowable = iterableObservable.toFlowable(BackpressureStrategy.BUFFER);
        iterableFlowable.map(s -> Log.e("time", System.currentTimeMillis()+s+""));

        //만약 여러 옵저버블을 동시에 배출 시키고 싶다면? => ConnectableObservable을 사용하면 된다!
        //cold 옵저버블을 hot 옵저버블로 변경해주는 역할을 한다.
        ConnectableObservable<Integer> connectObservable = Observable.range(0,100).publish();

        //구독은 일반적인 Observable과 동일하게 하면 된다.
        connectObservable.subscribe(n -> Log.e("number 1 : ", n+""));
        connectObservable.subscribe(n -> Log.e("number 2 :", n+""));

        //단, 연결을 해야 배출을 한다.
        connectObservable.connect();

        //Maybe
        Maybe<Integer> maybeInt = Maybe.just(1000);
        maybeInt.subscribe(this::checkNumber);

        Integer inte = 10;
        Maybe<Integer> maybenullInt = Maybe.just(inte);
        maybenullInt.subscribe(this::checkNumber, this::errorNumber);


    }

    private void printText(String string){
        Log.e("testObservable", string+"");
    }

    private void checkNumber(Integer integer) {
        Log.e("testNumber is : ", integer+"");
    }

    private void errorNumber(Throwable throwable){
        Log.e("error", throwable+"");
    }

}
