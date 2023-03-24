package com.example.myapplication.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.github.xiaohu409.htechart.R;
import com.github.xiaohu409.htechart.widget.HtWebViewClient;

public class HtEChartView extends LinearLayout {

    private WebView webView;

    public HtEChartView(Context context) {
        this(context, null);
    }

    public HtEChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HtEChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (layoutInflater == null) {
            return;
        }
        View view =layoutInflater.inflate(R.layout.ht_echart, this);
        webView = view.findViewById(R.id.web_view_id);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * 绑定数据
     * @param data
     */
    public void setData(String data) {
        if (webView == null) {
            return;
        }
        webView.setWebViewClient(new HtWebViewClient(data));
        webView.loadUrl("file:///android_asset/web/healthy.html");
    }
}
