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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView codeOne, codeTwo, codeThree, codeFour, codeFive, codeSix;
    EditText inputCodeEditText;
    InputMethodManager inputMethodManager;
    TextView codeText;

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

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        inputCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switch(s.length()){
                    case 1:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().clearColorFilter();
                        codeThree.getDrawable().clearColorFilter();
                        codeFour.getDrawable().clearColorFilter();
                        codeFive.getDrawable().clearColorFilter();
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeThree.getDrawable().clearColorFilter();
                        codeFour.getDrawable().clearColorFilter();
                        codeFive.getDrawable().clearColorFilter();
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeThree.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFour.getDrawable().clearColorFilter();
                        codeFive.getDrawable().clearColorFilter();
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeThree.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFour.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFive.getDrawable().clearColorFilter();
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeThree.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFour.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFive.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        break;
                    case 6:
                        codeOne.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeTwo.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeThree.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFour.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeFive.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeSix.getDrawable().setColorFilter(Color.parseColor("#E73828"), PorterDuff.Mode.SRC_ATOP);
                        codeText.setText(inputCodeEditText.getText().toString());
                        codeText.setVisibility(View.VISIBLE);
                        break;
                    default:
                        codeOne.getDrawable().clearColorFilter();
                        codeTwo.getDrawable().clearColorFilter();
                        codeThree.getDrawable().clearColorFilter();
                        codeFour.getDrawable().clearColorFilter();
                        codeFive.getDrawable().clearColorFilter();
                        codeSix.getDrawable().clearColorFilter();
                        codeText.setVisibility(View.INVISIBLE);
                        inputMethodManager.hideSoftInputFromWindow(inputCodeEditText.getWindowToken(), 0);
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.codeOne:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.codeTwo:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.codeThree:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.codeFour:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.codeFive:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
            case R.id.codeSix:
                inputCodeEditText.setText(null);
                inputCodeEditText.performClick();
                inputCodeEditText.requestFocus();
                inputMethodManager.showSoftInput(inputCodeEditText, InputMethodManager.SHOW_IMPLICIT);
                break;
        }
    }
}
