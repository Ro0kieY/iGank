package com.ro0kiey.igank;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IBaseView;
import com.ro0kiey.igank.mvp.view.IMeiziView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Module
public class MeiziModule {

    private IMeiziView view;
    private Context context;

    public MeiziModule(IMeiziView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IMeiziView provideIMeiziView(){
        return view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
