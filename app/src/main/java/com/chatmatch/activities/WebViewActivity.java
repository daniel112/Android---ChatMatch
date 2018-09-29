package com.chatmatch.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chatmatch.R;

public class WebViewActivity extends AppCompatActivity {
    Boolean isLogin;
    WebView webView;
    String homeUrl = "https://chatmatch.me";
    String loginUrl = "https://my.chatmatch.me/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        webView = findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        isLogin = bundle.getBoolean("isLogIn");

        if (isLogin) {
            webView.loadUrl(loginUrl);
        } else {
            webView.loadUrl(homeUrl);
        }

        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
