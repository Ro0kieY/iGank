package com.ro0kiey.igank.di.component;

import com.ro0kiey.igank.di.module.MeiziModule;
import com.ro0kiey.igank.ui.activity.MeiziActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Component(modules = MeiziModule.class)
public interface MeiziComponent {
    void inject(MeiziActivity activity);
}
