package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.view.IWebView;
import com.ro0kiey.igank.utils.ClipUtils;
import com.ro0kiey.igank.utils.ToastUtils;

/**
 * WebActivity的Presenter
 * Created by Ro0kieY on 2017/7/22.
 */

public class WebPresenter extends BasePresenter<IWebView> {

    public WebPresenter(IWebView iView, Context context) {
        super(iView, context);
    }

    public void initWebSettings(WebView webView, String url){
        iView.showProgress();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        //settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebViewClient(new ViewClient());
        webView.setWebChromeClient(new ChromeClient());
        webView.loadUrl(url);
        iView.hideProgress();

    }

    public void refresh(){
        iView.showProgress();
        iView.refresh();
        iView.hideProgress();
    }

    public void copyLink(String url){
        ClipUtils.copyToClipBoard(context, url);
        ToastUtils.ToastShort(context, R.string.copy_success);
    }

    public void openLink(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            ToastUtils.ToastShort(context, R.string.open_link_failed);
        }
    }

    private class ViewClient extends WebViewClient {

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().toString() != null) {
                view.loadUrl(request.getUrl().toString());
            }
            return true;
        }
    }

    private class ChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            iView.showNewProgress(newProgress);
            if (newProgress == 100) {
                iView.hideProgress();
            } else {
                iView.showProgress();
            }
        }

    }

}
