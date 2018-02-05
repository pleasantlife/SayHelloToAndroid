package com.gandan.android.permissionpractice;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //리퀘스트 코드를 설정해야한다.
    public static int REQ_CAMERA = 107;
    //또한 AndroidManifest.xml에 uses-permission을 설정해줘야 한다.
    //uses-permission을 설정해주지 않을 경우, 퍼미션 확인을 아예 무시한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 필요한 권한을 가진 퍼미션 String 배열을 만든다.
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        //2. 향상된 퍼미션 권한은 6.0(Nougat)부터 요구하기 떄문에, SDK 버전을 확인한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            //3. SDK 버전이 6.0이상인데, 이미 퍼미션을 받았다면...
            if((ContextCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(this, permissions[1]) == PackageManager.PERMISSION_GRANTED)){
                //3-1. 별도로 퍼미션을 받기 위해 할 것이 없으므로, 관련된 작업을 수행한다.
                Toast.makeText(this, "이미 퍼미션을 취득했습니다.", Toast.LENGTH_SHORT).show();
            }
            //4. 만약 퍼미션을 취득하지 못했다면
            else {
                //4-1. 안드로이드에서 기본으로 제공하는 requestPermissions() 함수를 사용한다.
                requestPermissions(permissions, REQ_CAMERA);
                //requestPermissions를 통해 안드로이드에서 권한취득을 요청하기 위한 팝업창을 띄운다.
            }
        }
    }
}
