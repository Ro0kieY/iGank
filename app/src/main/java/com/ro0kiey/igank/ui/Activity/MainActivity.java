package com.ro0kiey.igank.ui.Activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv_meizi = (RecyclerView)findViewById(R.id.rv_meizi);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_meizi.setLayoutManager(layoutManager);
        meiziList = new ArrayList<>();
        adapter = new MeiziAdapter(meiziList);
        rv_meizi.addOnScrollListener(getLoadMoreListener(layoutManager));

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
                int[] lastPostition = new int[2];
                manager.findLastCompletelyVisibleItemPositions(lastPostition);
                if (lastPostition[1] >= adapter.getItemCount() - Config.LOAD_IMAGE_COUNT/2){
                    imageCount += Config.LOAD_IMAGE_COUNT;
                    loadMoreMeizi(imageCount, Config.LOAD_IMAGE_PAGE);
                    Runtime rt=Runtime.getRuntime();
                    long maxMemory=rt.maxMemory();
                    Log.d("maxMemory:", Long.toString(maxMemory / (1024 * 1024)));
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

    private void getMeiziData(int count, int page) {
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
                        adapter = new MeiziAdapter(resultsBean);
                        rv_meizi.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
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
}

