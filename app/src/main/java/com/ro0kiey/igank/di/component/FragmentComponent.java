package com.ro0kiey.igank.di.component;

import com.ro0kiey.igank.di.module.FragmentModule;
import com.ro0kiey.igank.ui.fragment.TabLayoutFragment;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/3.
 */

@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(TabLayoutFragment fragment);
}
