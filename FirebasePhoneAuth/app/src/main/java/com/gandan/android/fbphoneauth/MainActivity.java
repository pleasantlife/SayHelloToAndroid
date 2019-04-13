package com.gandan.android.fbphoneauth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    PhoneAuthProvider phoneAuthProvider;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callback;
    String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = "+8201012345678";

        firebaseAuth = FirebaseAuth.getInstance();
        phoneAuthProvider = PhoneAuthProvider.getInstance();
        callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.e("Success", phoneAuthCredential.getSmsCode()+"");
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.e("Fail", e.getMessage()+"");
            }
        };

        phoneAuthProvider.verifyPhoneNumber(
                phoneNumber,
                30,
                TimeUnit.SECONDS,
                this,
                callback
        );
    }
}
