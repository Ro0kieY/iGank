package com.ro0kiey.igank.ui.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.ro0kiey.igank.R;

/**
 * Created by Ro0kieY on 2017/7/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    //private AppBarLayout appBarLayout;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        //appBarLayout = (AppBarLayout)findViewById(R.id.appbarlayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected abstract int getLayoutId();
}
