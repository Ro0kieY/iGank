package com.ro0kiey.igank.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.presenter.BasePresenter;
import com.ro0kiey.igank.mvp.view.IBaseView;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.utils.ShareUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ro0kieY on 2017/7/4.
 */

public class AboutActivity extends BaseActivity<BasePresenter> implements IBaseView {

    @BindView(R.id.about_image_view)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    private void initView() {
        collapsingToolbar.setTitle("About Me");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            collapsingToolbar.setCollapsedTitleTextColor(getColor(R.color.White));
        }
        Glide.with(this).load(R.mipmap.ic_launcher).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.share:
                ShareUtils.shareApp(this, R.string.share_app, R.string.share_app_to_friend);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}

