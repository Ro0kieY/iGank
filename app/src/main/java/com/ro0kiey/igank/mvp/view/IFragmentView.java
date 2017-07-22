package com.ro0kiey.igank.mvp.view;

import com.ro0kiey.igank.model.Bean.GankBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/22.
 */

public interface IFragmentView extends IBaseView {

    void showMoreList(List<GankBean> gankBean);

    void showLoadFailed();

    void showListData(List<GankBean> gankBean);

}
