package com.ro0kiey.igank.di.component;

import com.ro0kiey.igank.di.module.ActivityModule;
import com.ro0kiey.igank.ui.activity.GankActivity;
import com.ro0kiey.igank.ui.activity.ListActivity;
import com.ro0kiey.igank.ui.activity.MainActivity;
import com.ro0kiey.igank.ui.activity.MeiziActivity;
import com.ro0kiey.igank.ui.activity.VideoActivity;
import com.ro0kiey.igank.ui.activity.WebActivity;

import dagger.Component;

/**
 * Created by Ro0kieY on 2017/8/3.
 */

@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void injectMainActivity(MainActivity activity);

    void injectGankActivity(GankActivity activity);

    void injectListActivity(ListActivity activity);

    void injectMeiziActivity(MeiziActivity activity);

    void injectVideoActivity(VideoActivity activity);

    void injectWebActivity(WebActivity activity);

}
