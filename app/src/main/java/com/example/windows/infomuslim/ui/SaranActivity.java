package com.example.windows.infomuslim.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.windows.infomuslim.R;

public class SaranActivity extends AppCompatActivity {

    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://goo.gl/forms/W6fMsuREl9zIHgh93");

    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
            return;
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
