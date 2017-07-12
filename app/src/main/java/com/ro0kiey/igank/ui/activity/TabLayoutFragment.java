package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.ListAdapter;
import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.TypeList;

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

public class TabLayoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private View view;
    private String mParam;
    private List<GankBean> mGankBeanList = new ArrayList<>();

    public static TabLayoutFragment newInstance(String param){
        TabLayoutFragment fragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", param);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam = getArguments().getString("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tablayout_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView)view.findViewById(R.id.fragment_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        switch (mParam){
            case "Android":
                getListData("Android", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "IOS":
                getListData("iOS", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "APP":
                getListData("App", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "瞎推荐":
                getListData("瞎推荐", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "拓展资源":
                getListData("拓展资源", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "前端":
                getListData("前端", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            case "休息视频":
                getListData("休息视频", Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_PAGE);
                break;
            default:
                break;
        }
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void getListData(String type, int count, int page) {
        RetrofitClient.getApiServiceInstance().getListGank(type, count, page)
                .map(new Function<TypeList, List<GankBean>>() {
                    @Override
                    public List<GankBean> apply(@NonNull TypeList typeList) throws Exception {
                        return createListWithTypeList(typeList);
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

    private List<GankBean> createListWithTypeList(TypeList typeList) {
        if (typeList != null){
            for (int i = 0; i < typeList.getResults().size(); i++){
                mGankBeanList.add(typeList.getResults().get(i));
            }
        }
        return mGankBeanList;
    }


}
