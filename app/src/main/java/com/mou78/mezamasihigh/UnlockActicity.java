package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URL;

public class UnlockActicity extends AppCompatActivity {
    // WebView初期化
    WebView myWebView = null;
    URL url;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock_acticity);

        WebView webView1 = (WebView)findViewById(R.id.webView);
        webView1.setWebViewClient(new WebViewClient() {
        });


        // WebView呼び出し
        myWebView = (WebView) findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("file:///android_asset/index.html"); // ローカルのhtmlファイルを指定

        // WebView内のJavaScriptの実行を許可
        myWebView.getSettings().setJavaScriptEnabled(true);
        //Java呼び出し
        myWebView.loadUrl("javascript:addCount();");

    }
}