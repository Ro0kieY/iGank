package com.ro0kiey.igank.mvp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.GankAdapter;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.mvp.presenter.GankPresenter;
import com.ro0kiey.igank.mvp.view.IGankView;
import com.ro0kiey.igank.utils.ShareUtils;
import com.ro0kiey.igank.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class GankActivity extends BaseActivity<GankPresenter> implements IGankView {

    @BindView(R.id.gank_image_view)
    ImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_gank)
    RecyclerView rv_gank;
    @BindView(R.id.activity_gank_fab)
    FloatingActionButton fab;

    private GankAdapter adapter;
    private List<GankBean> mGankBean = new ArrayList<>();
    private ActionBar actionBar;
    private GankPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initView();
        initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank;
    }

    @Override
    protected void initPresenter() {
        this.mPresenter = new GankPresenter(this, this);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);

        fab.setVisibility(View.INVISIBLE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_gank.setLayoutManager(layoutManager);
        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        String meiziUrl = getIntent().getStringExtra("Url");
        Glide.with(this).load(meiziUrl).centerCrop().into(imageView);
    }

    private void initData() {

        String date = getIntent().getStringExtra("date");
        int year = Integer.valueOf(date.substring(0, 4));
        Log.d("GankActivity", "year: " + year);
        int month = Integer.valueOf(date.substring(5, 7));
        Log.d("GankActivity", "month: " + month);
        int day = Integer.valueOf(date.substring(8, 10));
        Log.d("GankActivity", "day: " + day);
        actionBar.setTitle(date.substring(0, 10));

        mPresenter.getDailyGank(year, month, day);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GankActivity.this, VideoActivity.class);
                intent.putExtra("title", mGankBean.get(0).getDesc());
                intent.putExtra("Url", mGankBean.get(0).getUrl());
                startActivity(intent);
            }
        });
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gank_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.share:
                ShareUtils.shareApp(this, R.string.share_app, R.string.share_app_to_friend);
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        fab.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showGankData(List<GankBean> gankBean) {
        mGankBean.clear();
        mGankBean.addAll(gankBean);
        adapter = new GankAdapter(mGankBean);
        rv_gank.setAdapter(adapter);
    }

    @Override
    public void showGetDataFailed() {
        ToastUtils.SnackBarShort(rv_gank, R.string.gank_failed);
    }
}
