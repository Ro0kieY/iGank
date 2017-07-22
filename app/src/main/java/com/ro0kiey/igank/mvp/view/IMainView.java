package com.ro0kiey.igank.mvp.view;

import com.ro0kiey.igank.model.Bean.MeiziBean;

import java.util.List;

/**
 * MainActivity的View接口
 * Created by Ro0kieY on 2017/7/19.
 */

public interface IMainView extends IBaseView {

    void showErrorView();

    void showMeiziData(List<MeiziBean> meiziBean);

    void showMoreMeizi(List<MeiziBean> meiziBean);

}
