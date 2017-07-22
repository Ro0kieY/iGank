package com.ro0kiey.igank.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ro0kiey.igank.mvp.presenter.BasePresenter;


/**
 * Fragment的基类
 * Created by Ro0kieY on 2017/7/13.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected View view;
    //是否可见的标志位
    protected boolean isVisible;
    //Fragment是否已经初始化完成的标志位
    public boolean isPrepared = false;
    //是否已经加载过数据的标志位
    protected boolean hasLoaded = false;

    /**
     * 实现Fragment懒加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * setUserVisibleHint为false时调用的方法
     */
    protected void onInvisible() {
        if (hasLoaded){
            invisibleEvent();
        }
    }

    /**
     * setUserVisibleHint为true时调用的方法
     */
    protected void onVisible(){
        lazyLoad();
    }

    protected void lazyLoad(){
        //确保View初始化完成
        if (!isVisible || !isPrepared) {
            return;
        }
        //加载数据
        if (!hasLoaded) {//如果没有加载过数据
            initLazyLoadData();
            hasLoaded = true;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = View.inflate(getActivity(), getLayoutId(), null);
        }
        ViewGroup parent = (ViewGroup)view.getParent();
        if (parent != null){
            parent.removeView(view);
        }

        initView();
        isPrepared = true;
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lazyLoad();
        initData();
        initEvent();
    }

    /**
     * 布局xml文件的id
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    /**
     * 在onCreateView中调用，可以执行findViewById操作
     */
    protected abstract void initView();

    /**
     * 初始化懒加载的数据
     */
    protected abstract void initLazyLoadData();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 加载过数据后，fragment变为不可见之后的需要执行的操作
     */
    protected abstract void invisibleEvent();


    public String getClassName() {
        return getClass().getSimpleName();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume"+"----->"+getClassName());
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("onPause"+"----->"+getClassName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("onDestroyView"+"----->"+getClassName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy"+"----->"+getClassName());
    }
}
