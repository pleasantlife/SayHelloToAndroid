package com.gandan.android.estimatecalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    EditText inputPriceOne, inputPriceTwo, inputPriceThree, inputPriceFour, inputPriceFive;
    RadioGroup vatGroup;
    RadioButton btnVatInclude, btnVatExclude;
    int one, two, three, four, five;
    double total = 0;
    TextView txtSumPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputPriceOne = findViewById(R.id.inputPriceOne);
        inputPriceTwo = findViewById(R.id.inputPriceTwo);
        inputPriceThree = findViewById(R.id.inputPriceThree);
        inputPriceFour = findViewById(R.id.inputPriceFour);
        inputPriceFive = findViewById(R.id.inputPriceFive);
        txtSumPrice = findViewById(R.id.txtSumPrice);
        btnVatInclude = findViewById(R.id.btnVatInclude);
        btnVatExclude = findViewById(R.id.btnVatExclude);
        vatGroup = findViewById(R.id.vatGroup);


        Observable<CharSequence> priceOneObservable = RxTextView.textChanges(inputPriceOne);
        Observable<CharSequence> priceTwoObservable = RxTextView.textChanges(inputPriceTwo);
        Observable<CharSequence> priceThreeObservable = RxTextView.textChanges(inputPriceThree);
        Observable<CharSequence> priceFourObservable = RxTextView.textChanges(inputPriceFour);
        Observable<CharSequence> priceFiveObservable = RxTextView.textChanges(inputPriceFive);

        priceOneObservable.subscribe( oneString -> {
            if (oneString.length() > 0) {
                one = Integer.parseInt(oneString.toString());
            } else {
                one = 0;
            }
            grandTotal();
        });

        /*RxTextView.textChanges(inputPriceOne).subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CharSequence charSequence) {
                if(charSequence.length() > 0) {
                    one = Integer.parseInt(charSequence.toString());
                    grandTotal();
                }else {
                    one = 0;
                    grandTotal();
                }
                Log.e("one", one+"");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/

        priceTwoObservable.subscribe( twoString -> {
            if (twoString.length() > 0) {
                two = Integer.parseInt(twoString.toString());
            } else {
                two = 0;
            }
            grandTotal();
        });

        priceThreeObservable.subscribe( threeString -> {
            if (threeString.length() > 0) {
                three = Integer.parseInt(threeString.toString());
            } else {
                three = 0;
            }
            grandTotal();
        });

        priceFourObservable.subscribe( fourString -> {
            if (fourString.length() > 0) {
                four = Integer.parseInt(fourString.toString());
            } else {
                four = 0;
            }
            grandTotal();
        });

        priceFiveObservable.subscribe( fiveString -> {
            if (fiveString.length() > 0) {
                five = Integer.parseInt(fiveString.toString());
            } else {
                five = 0;
            }
            grandTotal();
        });

    }


    private void grandTotal(){
        total = one+two+three+four+five;
        vatGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.btnVatInclude){
                    total = total * 1.1;
                    Log.e("checked", checkedId+"");
                    txtSumPrice.setText((int) total + "원");
                }
            }
        });
        txtSumPrice.setText((int) total + "원");
    }

}
