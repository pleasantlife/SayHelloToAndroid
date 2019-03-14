package com.android.gandan.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast 커스텀
        Toast toast = Toast.makeText(this, "Gravity Test", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);


    }
}
