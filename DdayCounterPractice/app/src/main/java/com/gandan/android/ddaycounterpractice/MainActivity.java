package com.gandan.android.ddaycounterpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputYear, inputMonth, inputDate;
    long dDaylong;
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




        /*SimpleDateFormat dDayFormat = new SimpleDateFormat("yyyy년 MM월 DD일", Locale.KOREA);
        String startTime = dDayFormat.format(getTime());
        String nowTime = dDayFormat.format(System.currentTimeMillis());
        Date startDate = null;
        Date nowDate = null;
        try {
            startDate = dDayFormat.parse(startTime);
            nowDate = dDayFormat.parse(nowTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long dDayTime = (startDate.getTime() - nowDate.getTime())/MILLISECOND_DAY;
        if(dDayTime == 0){
            txtItemDday.setText(R.string.d_day);
        } else if(dDayTime > 0) {
            txtItemDday.setText(context.getString(R.string.d_day_count, dDayTime));
        } else if(dDayTime < 0) {
            txtItemDday.setText("D+" + (dDayTime * -1) + "일");
        }*/
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCalculate:
                String stringYear = inputYear.getText().toString();
                String stringMonth = inputMonth.getText().toString();
                String stringDate = inputDate.getText().toString();


                SimpleDateFormat dDayFormat = new SimpleDateFormat("yyyy년 MM월 DD일", Locale.KOREA);

                String dDayTargetString = stringYear+"년"+stringMonth+"월"+stringDate+"일";
                String nowTime = dDayFormat.format(System.currentTimeMillis());
                try {
                    Date targetDate = dDayFormat.parse(dDayTargetString);
                    Date nowDate = dDayFormat.parse(nowTime);
                    dDaylong = (targetDate.getTime() - nowDate.getTime())/1000*60*60*24;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                txtResult.setText("D-"+ dDaylong + "일");
        }
    }
}
