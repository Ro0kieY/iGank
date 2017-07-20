package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IBaseView;

/**
 * 基础Presenter
 * Created by Ro0kieY on 2017/7/19.
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected T iView;
    protected Context context;

    public BasePresenter(T iView, Context context) {
        this.iView = iView;
        this.context = context;
    }

    public abstract void detachView();
}
