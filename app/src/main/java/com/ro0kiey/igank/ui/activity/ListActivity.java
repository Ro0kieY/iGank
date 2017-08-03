package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.ViewPagerAdapter;
import com.ro0kiey.igank.di.component.DaggerActivityComponent;
import com.ro0kiey.igank.di.module.ActivityModule;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.mvp.presenter.ListPresenter;
import com.ro0kiey.igank.mvp.view.IListView;
import com.ro0kiey.igank.ui.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListActivity extends BaseActivity<ListPresenter> implements IListView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_tablayout)
    TabLayout tabLayout;
    @BindView(R.id.list_viewpager)
    ViewPager viewPager;

    @Inject
    ListPresenter mPresenter;

    private String[] types = new String[]{"ANDROID", "IOS", "APP", "前端", "拓展资源", "瞎推荐", "休息视频"};
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        DaggerActivityComponent.builder().activityModule(new ActivityModule(this, this)).build().injectListActivity(this);

        initView();
        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }

    private void initView() {

        //toolbar.setTitle(R.string.app_name);

        fragmentList = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            TabLayoutFragment fragment = TabLayoutFragment.newInstance(types[i]);
            fragmentList.add(fragment);
            /*TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("flag", String.valueOf(i));
            tabLayoutFragment.setArguments(bundle);
            fragmentList.add(tabLayoutFragment);*/
        }
    }

    private void initData() {

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), types, fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        String type = getIntent().getStringExtra("type");
        for (int i = 0; i < types.length; i++) {
            if (type.equals(types[i])) {
                //fragmentList.get(i).setUserVisibleHint(true);
                viewPager.setCurrentItem(i);
                tabLayout.getTabAt(i).select();
            }
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
