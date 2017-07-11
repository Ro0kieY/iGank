package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;

/**
 * Created by Ro0kieY on 2017/7/10.
 */

public class WebActivity extends BaseActivity {

    private WebView webView;
    private Toolbar toolbar;
    private WebSettings settings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initView();

        webView = (WebView)findViewById(R.id.activity_web_view);
        String VideoUrl = getIntent().getStringExtra("VideoUrl");
        setWebViewSettings(webView, VideoUrl);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setWebViewSettings(WebView webView, String Url) {
        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(Url);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

}