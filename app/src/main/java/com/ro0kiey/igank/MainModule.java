package com.ro0kiey.igank;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IBaseView;
import com.ro0kiey.igank.mvp.view.IMainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Module
public class MainModule {

    private IMainView view;
    private Context context;

    public MainModule(IMainView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IMainView provideIMainView(){
        return view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
