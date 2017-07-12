package com.ro0kiey.igank.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/12.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitleList;
    private List<Fragment> mFragmentList;

    public ViewPagerAdapter(FragmentManager fm, String[] mTitleList, List<Fragment> mFragmentList) {
        super(fm);
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
