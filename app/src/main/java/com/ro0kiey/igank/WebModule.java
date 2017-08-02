package com.ro0kiey.igank;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IMeiziView;
import com.ro0kiey.igank.mvp.view.IWebView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Module
public class WebModule {

    private IWebView view;
    private Context context;

    public WebModule(IWebView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IWebView provideIWebView(){
        return view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
