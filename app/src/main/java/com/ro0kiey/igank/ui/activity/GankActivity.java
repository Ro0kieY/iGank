package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class GankActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView)findViewById(R.id.gank_image_view) ;

        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        String meiziUrl = getIntent().getStringExtra("Url");
        Glide.with(this).load(meiziUrl).centerCrop().into(imageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank;
    }
}
