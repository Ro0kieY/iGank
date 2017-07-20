package com.ro0kiey.igank.mvp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.MeiziAdapter;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.mvp.presenter.MainPresenter;
import com.ro0kiey.igank.mvp.view.IMainView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ro0kieY on 2017/7/19.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {

    @BindView(R.id.appbarlayout)
    AppBarLayout appbarlayout;
    @BindView(R.id.rv_meizi)
    RecyclerView rv_meizi;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private MainPresenter mPresenter;
    private List<MeiziBean> meiziList = new ArrayList<>();
    private MeiziAdapter adapter;
    private int count = Config.LOAD_IMAGE_COUNT;
    private int[] lastPostition = new int[2];
    private int[] lastCompletePosition = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        initView();
        initData();
    }

    private void initData() {
        mPresenter.getMeiziData(count, Config.LOAD_IMAGE_PAGE);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    public void initView() {

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_meizi.setLayoutManager(layoutManager);
        rv_meizi.addOnScrollListener(getLoadMoreListener(layoutManager));
        adapter = new MeiziAdapter(meiziList);
        rv_meizi.setAdapter(adapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.Black);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshMeiziData(count, Config.LOAD_IMAGE_PAGE);
            }
        });
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showMeiziData(List<MeiziBean> meiziBean) {
        meiziList.clear();
        meiziList.addAll(meiziBean);
        //meiziList = meiziBean;
        adapter.notifyDataSetChanged();
    }

    private RecyclerView.OnScrollListener getLoadMoreListener(final StaggeredGridLayoutManager manager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d("onScrolled", "dy " + dy);
                manager.findLastVisibleItemPositions(lastPostition);
                manager.findLastCompletelyVisibleItemPositions(lastCompletePosition);
                if (lastPostition[1] == adapter.getItemCount() - 1) {
                    count += Config.LOAD_IMAGE_COUNT;
                    mPresenter.loadMoreMeizi(count, Config.LOAD_IMAGE_PAGE);
                }
            }
        };
    }



    @OnClick(R.id.fab)
    public void onViewClicked() {
        rv_meizi.smoothScrollToPosition(0);
    }
}
