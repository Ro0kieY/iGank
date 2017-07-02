package com.ro0kiey.igank.ui.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.widget.ImageView;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.SharedElement;
import com.ro0kiey.igank.ui.base.BaseActivity;

public class MeiziActivity extends BaseActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView)findViewById(R.id.meizi_image);

        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        imageView.setImageDrawable(SharedElement.shareDrawable);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meizi;
    }

}
