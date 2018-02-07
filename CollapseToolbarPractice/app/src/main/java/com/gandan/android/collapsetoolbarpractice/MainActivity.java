package com.gandan.android.collapsetoolbarpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 *  CollapseToolbarLayout은 프로그래밍코드가 아닌 xml코드로 세팅할 수 있다.
 */

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://www.naver.com");
    }
}
