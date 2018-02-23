package com.gandan.android.collapsetoolbarpractice;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 *  CollapseToolbarLayout은 프로그래밍코드가 아닌 xml코드로 세팅할 수 있다.
 */

public class MainActivity extends AppCompatActivity {

    WebView webView;
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //맨 위로 올라간 듯한 효과를 주고 싶을 때, 또는 특정 방향으로의 스크롤 감지 시
        //아래의 코드로 간편하게 appBar를 띄울 수 있다...
        appBar.setExpanded(true);
        appBar.setExpanded(false);

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://www.naver.com");
    }
}
