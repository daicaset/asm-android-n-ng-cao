package com.example.hp8440p.myapplication.News;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hp8440p.myapplication.R;

public class webViewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        setTitle("News");
        webView = findViewById(R.id.Wview);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);

    }
}
