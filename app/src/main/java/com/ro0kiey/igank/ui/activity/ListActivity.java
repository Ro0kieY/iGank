package com.ro0kiey.igank.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.adapter.ViewPagerAdapter;
import com.ro0kiey.igank.mvp.presenter.BasePresenter;
import com.ro0kiey.igank.mvp.view.IMeiziView;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.ui.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseActivity<BasePresenter<IMeiziView>> {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] types = new String[]{"ANDROID", "IOS", "APP", "前端", "拓展资源", "瞎推荐", "休息视频"};
    private List<Fragment> fragmentList;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout)findViewById(R.id.list_tablayout);
        viewPager = (ViewPager)findViewById(R.id.list_viewpager);

        fragmentList = new ArrayList<>();
        for (int i = 0; i < types.length; i++){
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

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), types, fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        String type = getIntent().getStringExtra("type");
        for (int i = 0; i < types.length; i++){
            if (type.equals(types[i])){
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
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_list;
    }
}
