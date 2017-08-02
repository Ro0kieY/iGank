package com.ro0kiey.igank.di.component;

import com.ro0kiey.igank.di.module.WebModule;
import com.ro0kiey.igank.ui.activity.VideoActivity;
import com.ro0kiey.igank.ui.activity.WebActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Component(modules = WebModule.class)
public interface WebComponent {
    void inject(WebActivity activity);
    void inject(VideoActivity activity);
}
