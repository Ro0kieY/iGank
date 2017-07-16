package com.ro0kiey.igank.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.ListAdapter;
import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.GankList;
import com.ro0kiey.igank.ui.base.BaseFragment;
import com.ro0kiey.igank.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ro0kieY on 2017/7/12.
 */

public class TabLayoutFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private String mParam;
    private List<GankBean> mGankBeanList = new ArrayList<>();
    private int lastPosition;
    private int lastCompletePosition;
    private int mListCount = Config.LOAD_LIST_COUNT;

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
        if (getArguments() != null) {
            mParam = getArguments().getString("type");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tablayout_fragment;
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.addOnScrollListener(getLoadMoreListener(layoutManager));
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initLazyLoadData() {
        switch (mParam) {
            case "ANDROID":
                mParam = "Android";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "IOS":
                mParam = "iOS";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "APP":
                mParam = "App";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "瞎推荐":
                mParam = "瞎推荐";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "拓展资源":
                mParam = "拓展资源";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "前端":
                mParam = "前端";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            case "休息视频":
                mParam = "休息视频";
                getListData(mParam, Config.LOAD_LIST_COUNT, Config.LOAD_LIST_PAGE);
                break;
            default:
                break;
        }
        recyclerView.setAdapter(adapter);
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

    private RecyclerView.OnScrollListener getLoadMoreListener(final LinearLayoutManager manager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.d("onScrolled", "dy " + dy);
                lastCompletePosition = manager.findLastCompletelyVisibleItemPosition();
                lastPosition = manager.findLastVisibleItemPosition();
                if (lastPosition >= adapter.getItemCount() - 3) {
                    mListCount += Config.LOAD_LIST_COUNT;
                    getMoreList(mParam, mListCount, Config.LOAD_LIST_PAGE);
                }
            }
        };
    }

    private void getMoreList(String type, int count, int page) {

        RetrofitClient.getApiServiceInstance().getListGank(type, count, page)
                .map(new Function<GankList, List<GankBean>>() {
                    @Override
                    public List<GankBean> apply(@NonNull GankList gankList) throws Exception {
                        //int oldSize = mGankBeanList.size();
                        //mGankBeanList.clear();
                        //adapter.notifyItemRangeRemoved(0, oldSize);
                        return addMoreListWithTypeList(gankList);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GankBean>>() {
                    @Override
                    public void accept(@NonNull List<GankBean> gankBeen) throws Exception {
                        //adapter.notifyDataSetChanged();
                        adapter.notifyItemRangeChanged(mGankBeanList.size() - Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_COUNT);
                        //adapter.notifyItemRangeInserted(0, mGankBeanList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (lastPosition == lastCompletePosition){
                            ToastUtils.SnackBarShort(view, R.string.load_list_failed);
                        } else {
                            return;
                        }
                    }
                });
    }

    private void getListData(String type, int count, int page) {
        RetrofitClient.getApiServiceInstance().getListGank(type, count, page)
                .map(new Function<GankList, List<GankBean>>() {
                    @Override
                    public List<GankBean> apply(@NonNull GankList gankList) throws Exception {
                        //mGankBeanList.clear();
                        return createListWithTypeList(gankList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GankBean>>() {
                    @Override
                    public void accept(@NonNull List<GankBean> gankBean) throws Exception {
                        adapter = new ListAdapter(mGankBeanList);
                        recyclerView.setAdapter(adapter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Snackbar.make(view, "无法获得详细信息", Snackbar.LENGTH_SHORT).show();
                    }
                });
    }

    private List<GankBean> createListWithTypeList(GankList gankList) {
        if (gankList != null) {
            for (int i = 0; i < gankList.getResults().size(); i++) {
                mGankBeanList.add(gankList.getResults().get(i));
            }
        }
        return mGankBeanList;
    }

    private List<GankBean> addMoreListWithTypeList(GankList gankList) {
        if (gankList != null){
            for (int i = mGankBeanList.size(); i < gankList.getResults().size(); i++){
                mGankBeanList.add(gankList.getResults().get(i));
            }
        }
        return mGankBeanList;
    }
}
