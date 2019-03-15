package com.android.gandan.customview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast 커스텀
        Toast toast = Toast.makeText(this, "Gravity Test", Toast.LENGTH_SHORT);
        //중앙 정렬
        toast.setGravity(Gravity.CENTER, 0, 0);
        //아래쪽에 정렬
        toast.setGravity(Gravity.BOTTOM, 0, 300);
        toast.show();

        //다이얼로그 위치 조정
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("HEllo").create();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        //y값이 0보다 크면 아래로 내려가고, 0보다 작으면 위로 올라간다.
        lp.y = 300;
        dialog.getWindow().setAttributes(lp);
        dialog.show();


    }
}
