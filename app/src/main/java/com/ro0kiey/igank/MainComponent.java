package com.ro0kiey.igank;

import com.ro0kiey.igank.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
