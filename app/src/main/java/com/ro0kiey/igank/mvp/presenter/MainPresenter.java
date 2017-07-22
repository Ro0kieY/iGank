package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.ro0kiey.igank.http.RetrofitClient;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.model.Meizi;
import com.ro0kiey.igank.model.休息视频;
import com.ro0kiey.igank.mvp.AboutActivity;
import com.ro0kiey.igank.mvp.view.IMainView;
import com.ro0kiey.igank.mvp.ListActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * MainActivity的Presenter
 * Created by Ro0kieY on 2017/7/19.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(IMainView iView, Context context) {
        super(iView, context);
    }

    public void getMeiziData(int count, int page){
        iView.showProgress();
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
                return createMeiziListWithMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        iView.showMeiziData(meiziBean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showErrorView();
                    }
                });
        iView.hideProgress();
    }

    public void loadMoreMeizi(final int count, int page) {
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
                return addMoreMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        iView.showMoreMeizi(meiziBean);
                        //iView.showMeiziData(meiziBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showErrorView();
                    }
                });
    }

    public void refreshMeiziData(final int count, int page) {
        iView.showProgress();
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
                return createMeiziListWithMeizi(meizi);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MeiziBean>>() {
                    @Override
                    public void accept(@NonNull List<MeiziBean> meiziBean) throws Exception {
                        iView.showMeiziData(meiziBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showErrorView();
                    }
                });
        iView.hideProgress();
    }

    private List<MeiziBean> createMeiziListWithMeizi(Meizi meizi) {
        List<MeiziBean> meiziList = new ArrayList<>();
        if (meizi != null){
            for (int i = 0; i < meizi.results.size(); i++){
                meiziList.add(meizi.results.get(i));
            }
        }
        return meiziList;
    }

    private Meizi createMeiziWith休息视频(Meizi meizi, 休息视频 休息视频) {
        for (int i = 0; i < meizi.results.size(); i++) {
            meizi.results.get(i).setDesc(meizi.results.get(i).getDesc() + " " + 休息视频.results.get(i).getDesc());
            meizi.results.get(i).setWho(休息视频.results.get(i).getWho());
        }
        return meizi;
    }

    private List<MeiziBean> addMoreMeizi(Meizi meizi) {
        List<MeiziBean> meiziList = new ArrayList<>();
        if (meizi != null){
            for (int i = meiziList.size(); i < meizi.results.size(); i++){
                meiziList.add(meizi.results.get(i));
            }
        }
        return meiziList;
    }

    public void toAboutActivity(){
        Intent intent = new Intent(this.context, AboutActivity.class);
        this.context.startActivity(intent);
    }


    public void toListActivity() {
        Intent intent = new Intent(this.context, ListActivity.class);
        intent.putExtra("type", "ANDROID");
        this.context.startActivity(intent);
    }

}
