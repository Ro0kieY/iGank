package com.ro0kiey.igank.ui.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.MeiziAdapter;
import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Meizi;
import com.ro0kiey.igank.model.MeiziBean;
import com.ro0kiey.igank.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private RecyclerView rv_meizi;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<MeiziBean> meiziList;
    private MeiziAdapter adapter;
    private FloatingActionButton fab;
    private StaggeredGridLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv_meizi = (RecyclerView)findViewById(R.id.rv_meizi);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_meizi.setLayoutManager(layoutManager);
        meiziList = new ArrayList<>();
        adapter = new MeiziAdapter(meiziList, rv_meizi);
        rv_meizi.addOnScrollListener(getLoadMoreListener(layoutManager));

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_meizi.smoothScrollToPosition(0);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.lightPurple);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        getMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
    }

    private RecyclerView.OnScrollListener getLoadMoreListener(final StaggeredGridLayoutManager manager) {
        return new RecyclerView.OnScrollListener() {
            int imageCount = Config.LOAD_IMAGE_COUNT;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d("onScrolled", "dy " + dy);
                int[] lastPostition = new int[2];
                manager.findLastVisibleItemPositions(lastPostition);
                if (lastPostition[1] >= adapter.getItemCount() - 2){
                    imageCount += Config.LOAD_IMAGE_COUNT;
                    loadMoreMeizi(imageCount, Config.LOAD_IMAGE_PAGE);
                }
            }
        };
    }

    private void loadMoreMeizi(int count, int page) {
        RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, List<MeiziBean>>() {
                    @Override
                    public List<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                        meiziList = addMoreMeizi(meizi);
                        return meiziList;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> resultsBean) throws Exception {
                        Log.d("MainActivity", "loadmore");
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void refreshMeiziData(int count, int page) {
        RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, List<MeiziBean>>() {
                    @Override
                    public List<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                        meiziList.clear();
                        meiziList = addAllMeizi(meizi);
                        return meiziList;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> resultsBean) throws Exception {
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMeiziData(final int count, int page) {
        RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, List<MeiziBean>>() {
                    @Override
                    public List<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.toString());
                        return meiziList = addAllMeizi(meizi);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> resultsBean) throws Exception {
                        adapter = new MeiziAdapter(resultsBean, rv_meizi);
                        rv_meizi.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Snackbar.make(rv_meizi, "加载失败，请检查wifi是否连接...", Snackbar.LENGTH_LONG)
                                .setAction("重试", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refreshMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                                    }
                                })
                                .show();
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    private List<MeiziBean> addAllMeizi(Meizi meizi) {
        if (meizi != null){
            for (int i = 0; i < meizi.results.size(); i++){
                meiziList.add(meizi.results.get(i));
            }
        }
        return meiziList;
    }

    private List<MeiziBean> addMoreMeizi(Meizi meizi) {
        if (meizi != null){
            for (int i = (meizi.results.size() - 1); i > meizi.results.size() - Config.LOAD_IMAGE_COUNT + 1; i--){
                meiziList.add(meizi.results.get(i));
            }
        }
        return meiziList;
    }
}

