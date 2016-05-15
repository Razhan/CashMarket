package com.example.cashmarket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;


public class Help extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        webView = (WebView) findViewById(R.id.help);
        String customHtml = "<html><body><h1>Hello, WebView</h1></body></html>";
        webView.loadUrl("file:///android_asset/help.html");

    }
}
