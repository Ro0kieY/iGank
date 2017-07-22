package com.ro0kiey.igank.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.BaseActivity;
import com.ro0kiey.igank.mvp.presenter.WebPresenter;
import com.ro0kiey.igank.mvp.view.IWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * Created by Ro0kieY on 2017/7/11.
 */

public class WebActivity extends BaseActivity<WebPresenter> implements IWebView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.gank_detail_progress_bar)
    MaterialProgressBar progressBar;
    @BindView(R.id.gd_webview)
    WebView webView;

    private String url;
    private String title;
    private WebPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initPresenter() {
        this.mPresenter = new WebPresenter(this, this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initView();
        mPresenter.initWebSettings(webView, url);

    }

    private void initView() {

        url = getIntent().getStringExtra("Url");
        title = getIntent().getStringExtra("title");

        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gankdetail_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.refresh:
                mPresenter.refresh();
                break;
            case R.id.copy_link:
                mPresenter.copyLink(url);
                break;
            case R.id.open_link:
                mPresenter.openLink(url);
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

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNewProgress(int newProgress) {
        progressBar.setProgress(newProgress);
    }

    @Override
    public void refresh() {
        webView.reload();
    }

}
