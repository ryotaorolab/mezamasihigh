package com.mou78.mezamasihigh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
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
    //WebViewの上にある透明な画像
    public void not (View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://booked.jp/weather/tokyo-18247"));
        startActivity(intent);
    }

    //天気の詳細を見る（外部ブラウザ起動）
    public  void open (View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://booked.jp/weather/tokyo-18247"));
        startActivity(intent);
    }

    //閉じる
    public  void Close (View v) {
        this.finish();
        this.moveTaskToBack(true);
    }
}