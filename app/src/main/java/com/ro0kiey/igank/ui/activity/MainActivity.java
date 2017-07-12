package com.ro0kiey.igank.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.MeiziAdapter;
import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.model.Meizi;
import com.ro0kiey.igank.model.休息视频;
import com.ro0kiey.igank.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
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
    private int mImageCount = Config.LOAD_IMAGE_COUNT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rv_meizi = (RecyclerView)findViewById(R.id.rv_meizi);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_meizi.setLayoutManager(layoutManager);
        meiziList = new ArrayList<>();
        rv_meizi.addOnScrollListener(getLoadMoreListener(layoutManager));

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rv_meizi.smoothScrollToPosition(0);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.silver);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        getMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }

    private RecyclerView.OnScrollListener getLoadMoreListener(final StaggeredGridLayoutManager manager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d("onScrolled", "dy " + dy);
                int[] lastPostition = new int[2];
                manager.findLastVisibleItemPositions(lastPostition);
                if (lastPostition[1] == adapter.getItemCount() - 1){
                    mImageCount += Config.LOAD_IMAGE_COUNT;
                    loadMoreMeizi(mImageCount, Config.LOAD_IMAGE_PAGE);
                }
            }
        };
    }

    private void loadMoreMeizi(final int count, int page) {
        Observable.zip(RetrofitClient.getApiServiceInstance().getMeiziData(count, page),
                RetrofitClient.getApiServiceInstance().getShipinData(count, page),
                new BiFunction<Meizi, 休息视频, Meizi>() {
                    @Override
                    public Meizi apply(@NonNull Meizi meizi, @NonNull 休息视频 休息视频) throws Exception {
                        return createMeiziWith休息视频(meizi, 休息视频);

                    }
                }).map(new Function<Meizi, List<MeiziBean>>() {
            @Override
            public List<MeiziBean> apply(Meizi meizi) throws Exception {
                Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                meiziList.clear();
                return addAllMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        adapter.notifyItemRangeChanged(meiziList.size() - Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_COUNT);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Snackbar.make(rv_meizi, "加载更多失败，请检查wifi是否连接...", Snackbar.LENGTH_LONG)
                                .setAction("重试", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        refreshMeiziData(Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                                    }
                                })
                                .show();
                    }
                });


       /* RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, TypeList<MeiziBean>>() {
                    @Override
                    public TypeList<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                        meiziList.clear();
                        return addAllMeizi(meizi, );
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TypeList<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull TypeList<MeiziBean> resultsBean) throws Exception {
                        Log.d("MainActivity", "loadmore");
                        //adapter.notifyDataSetChanged();
                        adapter.notifyItemRangeChanged(meiziList.size() - Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_COUNT);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "loadMore Error", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void refreshMeiziData(final int count, int page) {
        Observable.zip(RetrofitClient.getApiServiceInstance().getMeiziData(count, page),
                RetrofitClient.getApiServiceInstance().getShipinData(count, page),
                new BiFunction<Meizi, 休息视频, Meizi>() {
                    @Override
                    public Meizi apply(@NonNull Meizi meizi, @NonNull 休息视频 休息视频) throws Exception {
                        return createMeiziWith休息视频(meizi, 休息视频);

                    }
                }).map(new Function<Meizi, List<MeiziBean>>() {
            @Override
            public List<MeiziBean> apply(Meizi meizi) throws Exception {
                Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                meiziList.clear();
                mImageCount = Config.LOAD_IMAGE_COUNT;
                return addAllMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        adapter.notifyDataSetChanged();
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


        /*RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, TypeList<MeiziBean>>() {
                    @Override
                    public TypeList<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.results.size() + meizi.toString());
                        meiziList.clear();
                        mImageCount = Config.LOAD_IMAGE_COUNT;
                        return addAllMeizi(meizi);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TypeList<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull TypeList<MeiziBean> resultsBean) throws Exception {
                        adapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "refresh Error", Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    private void getMeiziData(final int count, int page) {
        Observable.zip(RetrofitClient.getApiServiceInstance().getMeiziData(count, page),
                RetrofitClient.getApiServiceInstance().getShipinData(count, page),
                new BiFunction<Meizi, 休息视频, Meizi>() {
            @Override
            public Meizi apply(@NonNull Meizi meizi, @NonNull 休息视频 休息视频) throws Exception {
                return createMeiziWith休息视频(meizi, 休息视频);

            }
        }).map(new Function<Meizi, List<MeiziBean>>() {
            @Override
            public List<MeiziBean> apply(@NonNull Meizi meizi) throws Exception {
                return addAllMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        adapter = new MeiziAdapter(meiziBean);
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


        /*RetrofitClient.getApiServiceInstance().getMeiziData(count, page)
                .map(new Function<Meizi, TypeList<MeiziBean>>() {
                    @Override
                    public TypeList<MeiziBean> apply(Meizi meizi) throws Exception {
                        Log.d("MainActivity", "apply: " + meizi.toString());
                        return addAllMeizi(meizi);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TypeList<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull TypeList<MeiziBean> resultsBean) throws Exception {
                        adapter = new MeiziAdapter(resultsBean);
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
                });*/
    }

    private Meizi createMeiziWith休息视频(Meizi meizi, 休息视频 休息视频) {
        for (int i = 0; i < meizi.results.size(); i++) {
            meizi.results.get(i).setDesc(meizi.results.get(i).getDesc() + " " + 休息视频.results.get(i).getDesc());
            meizi.results.get(i).setWho(休息视频.results.get(i).getWho());
        }
        return meizi;
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
            for (int i = meizi.results.size() - Config.LOAD_IMAGE_COUNT; i < meizi.results.size(); i++){
                meiziList.add(meizi.results.get(i));
            }
        }
        return meiziList;
    }
}

