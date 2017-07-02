package com.ro0kiey.igank.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.ro0kiey.igank.R;

/**
 * Created by Ro0kieY on 2017/7/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setContentView(getLayoutId());

    }

    protected abstract int getLayoutId();

}
