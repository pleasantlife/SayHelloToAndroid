package com.gandan.android.rxandroidpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        Observable<String> setTextObservable = createButtonClickObservable();
        setTextObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                //textView.setText(s);
            }
        });

        setTextObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                if(d.isDisposed()){
                    d.dispose();
                }
            }

            @Override
            public void onNext(String s) {
                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private Observable<String> createButtonClickObservable(){
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String abc = editText.getText().toString();
                emitter.onNext(abc);
            }
        });
    }
}
