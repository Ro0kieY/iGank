package com.ro0kiey.igank.di.module;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IBaseView;
import com.ro0kiey.igank.mvp.view.IGankView;
import com.ro0kiey.igank.mvp.view.IListView;
import com.ro0kiey.igank.mvp.view.IMainView;
import com.ro0kiey.igank.mvp.view.IMeiziView;
import com.ro0kiey.igank.mvp.view.IWebView;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Ro0kieY on 2017/8/3.
 */

@Module
public class ActivityModule {

    private IBaseView view;
    private Context context;

    public ActivityModule(IBaseView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IMainView provideIMainView(){
        return (IMainView) view;
    }

    @Provides
    IGankView provideIGankView(){
        return (IGankView) view;
    }

    @Provides
    IListView provideIListView(){
        return (IListView) view;
    }

    @Provides
    IMeiziView provideIMeiziView(){
        return (IMeiziView) view;
    }

    @Provides
    IWebView provideIWebView(){
        return (IWebView) view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
