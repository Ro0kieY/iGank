package com.ro0kiey.igank.di.module;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IGankView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Module
public class GankModule {

    private IGankView view;
    private Context context;

    public GankModule(IGankView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IGankView provideIGankView(){
        return view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
