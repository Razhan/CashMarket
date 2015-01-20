package com.example.over1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Rules_webview extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules_webview);

        webView = (WebView) findViewById(R.id.rules_webview);
        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
        webView.loadUrl("file:///android_asset/rules.html");

    }

}
