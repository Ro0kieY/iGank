package com.ro0kiey.igank.mvp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.presenter.BasePresenter;

/**
 * Created by Ro0kieY on 2017/7/19.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPresenter();
    }

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}