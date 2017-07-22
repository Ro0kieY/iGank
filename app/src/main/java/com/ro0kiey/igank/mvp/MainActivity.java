package com.ro0kiey.igank.mvp;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.MeiziAdapter;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.mvp.presenter.MainPresenter;
import com.ro0kiey.igank.mvp.view.IMainView;
import com.ro0kiey.igank.ui.widget.IRecyclerView;

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
    IRecyclerView rv_meizi;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.actionmenu)
    FloatingActionMenu fab;
    @BindView(R.id.menu_item_to_list)
    FloatingActionButton floatButtonList;
    @BindView(R.id.menu_item_singleColoumLayout)
    FloatingActionButton floatButtonGrid;
    @BindView(R.id.menu_item_twoColoumLayout)
    FloatingActionButton floatButtonListStaggered;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private MainPresenter mPresenter;
    private List<MeiziBean> meiziList = new ArrayList<>();
    private MeiziAdapter adapter;
    private int count = Config.LOAD_IMAGE_COUNT;
    private StaggeredGridLayoutManager layoutManager;

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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv_meizi.setFloatActionMenu(fab);
        rv_meizi.setLoadMoreListener(new IRecyclerView.LoadMoreListener() {
            @Override
            public void loadMore() {
                count = count + Config.LOAD_IMAGE_COUNT;
                mPresenter.loadMoreMeizi(count, Config.LOAD_IMAGE_PAGE);
            }
        });
        rv_meizi.setLayoutManager(layoutManager);
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

    @Override
    public void showMoreMeizi(List<MeiziBean> meiziBean) {
        meiziList.clear();
        meiziList.addAll(meiziBean);
        adapter.notifyItemRangeChanged(count - Config.LOAD_IMAGE_COUNT, count - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                mPresenter.toAboutActivity();
                break;
            case R.id.refresh:
                count = Config.LOAD_IMAGE_COUNT;
                mPresenter.refreshMeiziData(count, Config.LOAD_IMAGE_PAGE);
                layoutManager.smoothScrollToPosition(rv_meizi, null, 0);
                break;
            default:
                break;
        }
        return false;
    }

    @OnClick(R.id.actionmenu)
    public void onViewClicked() {
        rv_meizi.smoothScrollToPosition(0);
    }

    @OnClick({R.id.actionmenu, R.id.menu_item_to_list, R.id.menu_item_singleColoumLayout, R.id.menu_item_twoColoumLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.actionmenu:
                rv_meizi.smoothScrollToPosition(0);
                break;
            case R.id.menu_item_to_list:
                mPresenter.toListActivity();
                break;
            case R.id.menu_item_singleColoumLayout:
                layoutManager.setSpanCount(1);
                break;
            case R.id.menu_item_twoColoumLayout:
                layoutManager.setSpanCount(2);
                break;
        }
    }
}
