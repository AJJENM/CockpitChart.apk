package com.example.myapplication.Util;
import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HtWebViewClient extends WebViewClient {

    private String data;

    public HtWebViewClient(String data) {
        this.data = data;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(final WebView view, String url) {
        super.onPageFinished(view, url);
        view.post(new Runnable() {
            @Override
            public void run() {
                view.loadUrl(String.format("javascript:setData(%s)", data));
            }
        });
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }


}

