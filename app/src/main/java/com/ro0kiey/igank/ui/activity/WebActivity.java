package com.ro0kiey.igank.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.utils.ClipUtils;
import com.ro0kiey.igank.utils.ToastUtils;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Ro0kieY on 2017/7/11.
 */

public class WebActivity extends BaseActivity {

    private Toolbar toolbar;
    private WebView webView;
    private String url;
    private String title;
    private MaterialProgressBar progressBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        url = getIntent().getStringExtra("Url");
        title = getIntent().getStringExtra("title");

        initView();

        webView = (WebView)findViewById(R.id.gd_webview);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new ViewClient());
        webView.setWebChromeClient(new ChromeClient());
        webView.loadUrl(url);
    }

    private void initView() {
        progressBar = (MaterialProgressBar) findViewById(R.id.gank_detail_progress_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gankdetail_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.refresh:
                webView.reload();
                break;
            case R.id.copy_link:
                ClipUtils.copyToClipBoard(this, url);
                ToastUtils.ToastShort(this, R.string.copy_success);
                break;
            case R.id.open_link:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(url);
                intent.setData(uri);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                } else {
                    ToastUtils.ToastShort(this, R.string.open_link_failed);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        //return false;
        return super.onKeyDown(keyCode, event);
    }

    private class ViewClient extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().toString() != null){
                view.loadUrl(request.getUrl().toString());
            }
            return true;
        }
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress == 100){
                progressBar.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        }
    }
}
