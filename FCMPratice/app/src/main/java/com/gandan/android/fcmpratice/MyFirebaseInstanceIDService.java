package com.gandan.android.fcmpratice;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by XPS on 2018-03-22.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {


    //FCM의 기기 토큰이 변경(기기가 변경되었을 떄)되면 실행되는 메소드.
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token", "Refreshed Token: "+refreshedToken);
    }
}
