package com.ro0kiey.igank.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.utils.ClipUtils;
import com.ro0kiey.igank.utils.ToastUtils;

/**
 * Created by Ro0kieY on 2017/7/11.
 */

public class GankDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private WebView webView;
    private String DetailUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gankdetail;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        DetailUrl = getIntent().getStringExtra("DetailUrl");
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
                webView.loadUrl(DetailUrl);
                break;
            case R.id.copy_link:
                ClipUtils.copyToClipBoard(this, DetailUrl);
                ToastUtils.ToastShort(this, R.string.copy_success);
                break;
            case R.id.open_link:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(DetailUrl);
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
}
