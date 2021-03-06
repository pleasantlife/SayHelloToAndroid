package com.gandan.android.pincodelayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CodeListener {

    ImageView codeOne, codeTwo, codeThree, codeFour, codeFive, codeSix;
    EditText inputCodeEditText;
    InputMethodManager inputMethodManager;
    TextView codeText;
    String colorString = "#E73828";
    List<ImageView> codeOvalImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputCodeEditText = findViewById(R.id.inputCodeEditText);
        codeOne = findViewById(R.id.codeOne);
        codeOne.setOnClickListener(this);
        codeTwo = findViewById(R.id.codeTwo);
        codeTwo.setOnClickListener(this);
        codeThree = findViewById(R.id.codeThree);
        codeThree.setOnClickListener(this);
        codeFour = findViewById(R.id.codeFour);
        codeFour.setOnClickListener(this);
        codeFive = findViewById(R.id.codeFive);
        codeFive.setOnClickListener(this);
        codeSix = findViewById(R.id.codeSix);
        codeSix.setOnClickListener(this);
        codeText = findViewById(R.id.codeText);

        codeOvalImages.add(codeOne);
        codeOvalImages.add(codeTwo);
        codeOvalImages.add(codeThree);
        codeOvalImages.add(codeFour);
        codeOvalImages.add(codeFive);
        codeOvalImages.add(codeSix);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        inputCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() >= 1 && s.length() <= 6){
                    setOvalColorFilter(s.length());
                } else {
                    clearColorFilter();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void setOvalColorFilter(int number){
        for(int i = 0; i < number; i++){
            codeOvalImages.get(i).getDrawable().setColorFilter(Color.parseColor(colorString), PorterDuff.Mode.SRC_ATOP);
        }
        for(int i = number-1; i >= 0; i--){
            codeOvalImages.get(i).clearColorFilter();
        }
        if(number == 6){
            codeText.setText(inputCodeEditText.getText().toString());
            codeText.setVisibility(View.VISIBLE);
            inputMethodManager.hideSoftInputFromWindow(inputCodeEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
            this.inputCode(inputCodeEditText.getText().toString());
        }
    }

    private void clearColorFilter(){
        for(ImageView imgCode : codeOvalImages){
            imgCode.getDrawable().clearColorFilter();
        }
        codeText.setVisibility(View.INVISIBLE);
        inputMethodManager.hideSoftInputFromWindow(inputCodeEditText.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    private void requestFocusToEditText(){
        inputCodeEditText.setText(null);
        inputCodeEditText.performClick();
        inputCodeEditText.requestFocus();
        inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onClick(View v) {
        if(v instanceof ImageView){
            requestFocusToEditText();
        }
    }

    //인터페이스를 사용해봄.
    @Override
    public void inputCode(String code) {
        Toast.makeText(this, code+"", Toast.LENGTH_SHORT).show();
    }
}
