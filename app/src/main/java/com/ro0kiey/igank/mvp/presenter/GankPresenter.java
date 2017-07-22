package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;

import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.model.DailyGank;
import com.ro0kiey.igank.mvp.view.IGankView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * GankActivity的Presenter
 * Created by Ro0kieY on 2017/7/22.
 */

public class GankPresenter extends BasePresenter<IGankView> {

    public GankPresenter(IGankView iView, Context context) {
        super(iView, context);
    }

    public void getDailyGank(int year, int month, int day){
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
                        iView.showGankData(gankBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showGetDataFailed();
                    }
                });
    }

    private List<GankBean> createGankBean(DailyGank dailyGank) {
        List<GankBean> mGankBean = new ArrayList<>();
        if (dailyGank.getResults().get休息视频() != null) {
            mGankBean.addAll(0, dailyGank.getResults().get休息视频());
        }
        if (dailyGank.getResults().getAndroid() != null) {
            mGankBean.addAll(dailyGank.getResults().getAndroid());
        }
        if (dailyGank.getResults().getiOS() != null) {
            mGankBean.addAll(dailyGank.getResults().getiOS());
        }
        if (dailyGank.getResults().get前端() != null) {
            mGankBean.addAll(dailyGank.getResults().get前端());
        }
        if (dailyGank.getResults().get拓展资源() != null) {
            mGankBean.addAll(dailyGank.getResults().get拓展资源());
        }
        if (dailyGank.getResults().get瞎推荐() != null) {
            mGankBean.addAll(dailyGank.getResults().get瞎推荐());
        }
        if (dailyGank.getResults().getAPP() != null) {
            mGankBean.addAll(dailyGank.getResults().getAPP());
        }
        return mGankBean;
    }
}
