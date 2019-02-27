package com.gandan.android.webviewpdfpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    String fileUrl = "https://www.samsungstf.or.kr/resources/down/SelfCheckList.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);
        if(fileUrl.contains(".pdf")){
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+fileUrl);
        } else {
            webView.loadUrl(fileUrl);
        }
    }
}
