package com.gandan.android.countdowntimer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    TextView ticktock;

    private final int SMS_PERMISSION = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkSmsPermission();

        ticktock = findViewById(R.id.ticktock);

        countDown();


    }

    private void countDown(){
        countDownTimer = new CountDownTimer(180000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String minute = millisUntilFinished/1000/60 + "";
                String second = (millisUntilFinished/1000)%60 + "";
                ticktock.setText(minute+"분 "+second+"초");
            }

            @Override
            public void onFinish() {
                ticktock.setText("Finish!");
                this.cancel();
            }
        };
        countDownTimer.start();
    }

    private void checkSmsPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = {Manifest.permission.READ_SMS};
                requestPermissions(permissions, SMS_PERMISSION);
            } else {
                countDown();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == SMS_PERMISSION){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                countDown();
            } else {
                ticktock.setText("Permission Denied!");
            }
        }
    }
}
