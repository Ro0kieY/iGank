package com.ro0kiey.igank.mvp.presenter;

import android.support.v4.app.Fragment;

import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.GankList;
import com.ro0kiey.igank.mvp.view.IFragmentView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Ro0kieY on 2017/7/22.
 */

public class FragmentPresenter extends BasePresenter<IFragmentView> {

    public FragmentPresenter(IFragmentView iView, Fragment fragment) {
        super(iView, fragment);
    }

    public void getMoreData(String type, int count, int page){
        RetrofitClient.getApiServiceInstance().getListGank(type, count, page)
                .map(new Function<GankList, List<GankBean>>() {
                    @Override
                    public List<GankBean> apply(@NonNull GankList gankList) throws Exception {
                        return createListWithTypeList(gankList);
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<GankBean>>() {
                    @Override
                    public void accept(@NonNull List<GankBean> gankBean) throws Exception {
                        iView.showMoreList(gankBean);
                        //adapter.notifyDataSetChanged();
                        //adapter.notifyItemRangeChanged(mGankBeanList.size() - Config.LOAD_IMAGE_COUNT, Config.LOAD_IMAGE_COUNT);
                        //adapter.notifyItemRangeInserted(0, mGankBeanList.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showLoadFailed();
                    }
                });
    }

    public void getListData(String type, int count, int page){
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
                        iView.showListData(gankBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showLoadFailed();
                    }
                });
    }

    private List<GankBean> createListWithTypeList(GankList gankList) {
        List<GankBean> mGankBeanList = new ArrayList<>();
        if (gankList != null) {
            for (int i = 0; i < gankList.getResults().size(); i++) {
                mGankBeanList.add(gankList.getResults().get(i));
            }
        }
        return mGankBeanList;
    }

}
