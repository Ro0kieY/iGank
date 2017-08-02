package com.ro0kiey.igank.di.module;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IListView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Module
public class ListModule {
    private IListView view;
    private Context context;

    public ListModule(IListView view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    IListView provideIListView(){
        return view;
    }

    @Provides
    Context provideContext(){
        return context;
    }
}
