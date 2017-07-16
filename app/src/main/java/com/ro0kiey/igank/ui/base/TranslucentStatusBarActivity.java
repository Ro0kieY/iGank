package com.ro0kiey.igank.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.utils.StatusBarUtils;

/**
 * Created by Ro0kieY on 2017/7/15.
 */

public abstract class TranslucentStatusBarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //private AppBarLayout appBarLayout;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //appBarLayout = (AppBarLayout)findViewById(R.id.appbarlayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StatusBarUtils.from(this)
                //沉浸状态栏
                .setTransparentStatusbar(true)
                //白底黑字状态栏
                .setLightStatusBar(true)
                //设置toolbar,actionbar等view
                .setActionbarView(toolbar)
                .process();

    }

    protected abstract int getLayoutId();

}
