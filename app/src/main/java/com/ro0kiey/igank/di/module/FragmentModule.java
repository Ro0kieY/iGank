package com.ro0kiey.igank.di.module;

import android.support.v4.app.Fragment;

import com.ro0kiey.igank.mvp.view.IFragmentView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ro0kieY on 2017/8/3.
 */

@Module
public class FragmentModule {

    private IFragmentView view;
    private Fragment fragment;

    public FragmentModule(IFragmentView view, Fragment fragment) {
        this.view = view;
        this.fragment = fragment;
    }

    @Provides
    IFragmentView provideIFragmentView(){
        return view;
    }

    @Provides
    Fragment provideFragment(){
        return fragment;
    }
}
