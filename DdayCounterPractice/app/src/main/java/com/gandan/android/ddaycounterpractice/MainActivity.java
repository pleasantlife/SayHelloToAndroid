package com.gandan.android.ddaycounterpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 *  안드로이드의 Calender 클래스를 이용해 간단한 D-Day 계산기를 만들어봅니다.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputYear, inputMonth, inputDate;
    Button btnCalculate;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = findViewById(R.id.btnCalculate);
        inputYear = findViewById(R.id.inputYear);
        inputMonth = findViewById(R.id.inputMonth);
        inputDate = findViewById(R.id.inputDate);
        txtResult = findViewById(R.id.txtResult);
        btnCalculate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCalculate:
                //사용자가 입력한 년/월/일을 int값으로 받아온다.
                int setYear = Integer.parseInt(inputYear.getText().toString());
                int setMonth = Integer.parseInt(inputMonth.getText().toString());
                int setDate = Integer.parseInt(inputDate.getText().toString());

                //Calender 생성
                Calendar setCalendar = Calendar.getInstance();

                //Calnedar에서 Month는 0~11월까지로 인식하기 때문에 사용자가 입력한 월에서 하나를 뺴야 한다.
                setCalendar.set(setYear, setMonth-1, setDate);

                //Calendar를 long값으로 변환한 후 현재 시간에서 뺀다.
                long dDaylong = System.currentTimeMillis() - setCalendar.getTimeInMillis();
                //계산한 값에 따라 D-Day를 기록한다.
                //1000*60*60*24는 24시간을 milliseconds로 환산한 값이다.
                if(dDaylong > 0){
                    txtResult.setText("D-"+dDaylong/(1000*60*60*24)+"일");
                } else if(dDaylong == 0){
                    txtResult.setText("D-Day");
                } else {
                    txtResult.setText("D+"+dDaylong/(1000*60*60*24)+"일");
                }
                break;
        }
    }
}
