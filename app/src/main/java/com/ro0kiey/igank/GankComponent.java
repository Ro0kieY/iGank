package com.ro0kiey.igank;

import com.ro0kiey.igank.ui.activity.GankActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/2.
 */
@Component(modules = GankModule.class)
public interface GankComponent {
    void inject(GankActivity activity);
}
