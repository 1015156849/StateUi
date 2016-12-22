package com.yzy.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yzy.stateui.BaseUIFragment;

import java.util.ArrayList;

/**
 * Created by 杨振宇
 * github地址：https://github.com/1015156849
 * on 2016/12/21.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseUIFragment> fragmentArrayList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<BaseUIFragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentArrayList.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
