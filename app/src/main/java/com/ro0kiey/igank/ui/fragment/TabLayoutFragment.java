package com.ro0kiey.igank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.ListAdapter;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.mvp.presenter.FragmentPresenter;
import com.ro0kiey.igank.mvp.view.IFragmentView;
import com.ro0kiey.igank.ui.base.BaseFragment;
import com.ro0kiey.igank.ui.widget.IRecyclerView;
import com.ro0kiey.igank.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ListActivity中的Fragment
 * Created by Ro0kieY on 2017/7/12.
 */

public class TabLayoutFragment extends BaseFragment<FragmentPresenter> implements IFragmentView {

    @BindView(R.id.fragment_recyclerview)
    IRecyclerView recyclerView;
    Unbinder unbinder;

    private ListAdapter adapter;
    private String mParam;
    private List<GankBean> mGankBeanList = new ArrayList<>();
    private int mListCount = Config.LOAD_LIST_COUNT;
    private FragmentPresenter mPresenter;

    public static TabLayoutFragment newInstance(String param) {
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this.getActivity());

        if (getArguments() != null) {
            mParam = getArguments().getString("type");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tablayout_fragment;
    }

    @Override
    protected void initPresenter() {
        this.mPresenter = new FragmentPresenter(this, this);
    }

    @Override
    protected void initView() {
        recyclerView = (IRecyclerView) view.findViewById(R.id.fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLoadMoreListener(new IRecyclerView.LoadMoreListener() {
            @Override
            public void loadMore() {
                mListCount += Config.LOAD_LIST_COUNT;
                mPresenter.getMoreData(mParam, mListCount, Config.LOAD_LIST_PAGE);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initLazyLoadData() {
        switch (mParam) {
            case "ANDROID":
                mParam = "Android";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "IOS":
                mParam = "iOS";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "APP":
                mParam = "App";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "瞎推荐":
                mParam = "瞎推荐";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "拓展资源":
                mParam = "拓展资源";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "前端":
                mParam = "前端";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "休息视频":
                mParam = "休息视频";
                mPresenter.getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void invisibleEvent() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMoreList(List<GankBean> gankBean) {
        mGankBeanList.clear();
        mGankBeanList.addAll(gankBean);
        adapter.notifyItemRangeChanged(mGankBeanList.size() - Config.LOAD_LIST_COUNT, Config.LOAD_LIST_COUNT);
    }

    @Override
    public void showLoadFailed() {
        ToastUtils.SnackBarShort(view, R.string.load_list_failed);
    }

    @Override
    public void showListData(List<GankBean> gankBean) {
        mGankBeanList.clear();
        mGankBeanList.addAll(gankBean);
        adapter = new ListAdapter(mGankBeanList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
