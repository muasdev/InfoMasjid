package com.example.windows.infomuslim.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.windows.infomuslim.R;

public class SaranActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://goo.gl/forms/W6fMsuREl9zIHgh93");

    }
}
