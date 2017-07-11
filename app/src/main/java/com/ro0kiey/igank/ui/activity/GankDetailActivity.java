package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;

/**
 * Created by Ro0kieY on 2017/7/11.
 */

public class GankDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gankdetail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        String DetailUrl = getIntent().getStringExtra("DetailUrl");
        webView = (WebView)findViewById(R.id.gd_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(DetailUrl);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
