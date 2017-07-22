package com.ro0kiey.igank.mvp.view;

import com.ro0kiey.igank.model.Bean.GankBean;

import java.util.List;

/**
 * GankActivity的View接口
 * Created by Ro0kieY on 2017/7/22.
 */

public interface IGankView extends IBaseView {

    void showGankData(List<GankBean> gankBean);

    void showGetDataFailed();

}
