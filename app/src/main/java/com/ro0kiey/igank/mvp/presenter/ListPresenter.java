package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;

import com.ro0kiey.igank.mvp.view.IListView;

/**
 * ListActivity的Presenter
 * Created by Ro0kieY on 2017/7/22.
 */

public class ListPresenter extends BasePresenter<IListView> {

    public ListPresenter(IListView iView, Context context) {
        super(iView, context);
    }

}
