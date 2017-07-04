package com.ro0kiey.igank.ui.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.ui.base.BaseActivity;

public class MeiziActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView)findViewById(R.id.meizi_image);

        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        String meiziUrl = getIntent().getStringExtra("Url");
        Glide.with(this).load(meiziUrl).into(imageView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meizi;
    }

}
