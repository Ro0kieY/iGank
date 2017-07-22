package com.ro0kiey.igank.mvp.view;

import android.net.Uri;

/**
 * MeiziActivity的View接口
 * Created by Ro0kieY on 2017/7/22.
 */

public interface IMeiziView extends IBaseView {

    void showSaveMeiziSuccess();

    void showHasSaved();

    void showErrorView();

    void showSaveMeiziFailed();

    void shareMeiziImage(Uri uri);

}
