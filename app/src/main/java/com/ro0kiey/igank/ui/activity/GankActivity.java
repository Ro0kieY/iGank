package com.ro0kiey.igank.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.GankAdapter;
import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.DailyGank;
import com.ro0kiey.igank.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class GankActivity extends BaseActivity {

    private ImageView imageView;
    private RecyclerView rv_gank;
    private GankAdapter adapter;
    private List<GankBean> mGankBean;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        toolbar = (Toolbar)findViewById(R.id.toolbar);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);

        imageView = (ImageView)findViewById(R.id.gank_image_view);
        fab = (FloatingActionButton)findViewById(R.id.activity_gank_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GankActivity.this, WebActivity.class);
                intent.putExtra("VideoUrl", mGankBean.get(0).getUrl());
                startActivity(intent);
            }
        });

        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        String meiziUrl = getIntent().getStringExtra("Url");
        String data = getIntent().getStringExtra("data");
        int year = Integer.valueOf(data.substring(0, 4));
        Log.d("GankActivity", "year: " + year);
        int month = Integer.valueOf(data.substring(5, 7));
        Log.d("GankActivity", "month: " + month);
        int day = Integer.valueOf(data.substring(8, 10));
        Log.d("GankActivity", "day: " + day);
        Glide.with(this).load(meiziUrl).centerCrop().into(imageView);

        rv_gank = (RecyclerView)findViewById(R.id.rv_gank);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_gank.setLayoutManager(layoutManager);

        getDailyGank(year, month, day);

    }

    private void getDailyGank(int year, int month, int day) {
        RetrofitClient.getApiServiceInstance().getDailyGank(year, month, day)
                .map(new Function<DailyGank, List<GankBean>>() {
                    @Override
                    public List<GankBean> apply(@NonNull DailyGank dailyGank) throws Exception {
                        return createGankBean(dailyGank);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GankBean>>() {
                    @Override
                    public void accept(@NonNull List<GankBean> gankBean) throws Exception {
                        adapter = new GankAdapter(gankBean);
                        rv_gank.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Snackbar.make(rv_gank, "Gank失败，已被反杀...", Snackbar.LENGTH_LONG)
                                .setAction("重试", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                                .show();
                    }
                });
    }

    private List<GankBean> createGankBean(DailyGank dailyGank) {
        mGankBean = new ArrayList<>();
        if (dailyGank.getResults().get休息视频() != null){
            mGankBean.addAll(0, dailyGank.getResults().get休息视频());
        }
        if (dailyGank.getResults().getAndroid() != null){
            mGankBean.addAll(dailyGank.getResults().getAndroid());
            }
        if (dailyGank.getResults().getiOS() != null){
            mGankBean.addAll(dailyGank.getResults().getiOS());
        }
        if (dailyGank.getResults().get前端() != null){
            mGankBean.addAll(dailyGank.getResults().get前端());
        }
        if (dailyGank.getResults().get拓展资源() != null){
            mGankBean.addAll(dailyGank.getResults().get拓展资源());
        }
        if (dailyGank.getResults().get瞎推荐() != null){
            mGankBean.addAll(dailyGank.getResults().get瞎推荐());
        }
        if (dailyGank.getResults().getAPP() != null){
            mGankBean.addAll(dailyGank.getResults().getAPP());
        }
        return mGankBean;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gank;
    }
}