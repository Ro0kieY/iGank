package com.ro0kiey.igank.di.component;

import com.ro0kiey.igank.di.module.ListModule;
import com.ro0kiey.igank.ui.activity.ListActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/2.
 */

@Component(modules = ListModule.class)
public interface ListComponent {
    void inject(ListActivity activity);
}
