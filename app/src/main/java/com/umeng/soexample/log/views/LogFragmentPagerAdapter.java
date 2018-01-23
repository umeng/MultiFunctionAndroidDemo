package com.umeng.soexample.log.views;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.umeng.soexample.log.BaseFragment;

/**
 * Created by wangfei on 2018/1/22.
 */

public class LogFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<BaseFragment> fragments;
    public LogFragmentPagerAdapter(FragmentManager fm,List<BaseFragment> fragments, Context context) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getFileName();
    }
}