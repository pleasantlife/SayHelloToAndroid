package com.gandan.android.ddaycounterpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
