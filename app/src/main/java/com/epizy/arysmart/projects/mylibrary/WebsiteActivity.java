package com.epizy.arysmart.projects.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {
    private WebView websiteWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        Intent intent = getIntent();
        if (null != intent){
            String url = intent.getStringExtra("url");//give this as field in future
            websiteWebView = findViewById(R.id.websiteWebView);
            websiteWebView.loadUrl(url);
            websiteWebView.setWebViewClient(new WebViewClient());//show website in our application

            //enable JavaScript in WebView
            websiteWebView.getSettings().setJavaScriptEnabled(true);
        }



    }

    //To go back in websites choice not go back in to the main menu
    @Override
    public void onBackPressed() {
        if (websiteWebView.canGoBack())
            websiteWebView.goBack();
        else
            super.onBackPressed();
    }
}