package com.jeeb.farsialifba.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jeeb.farsialifba.fragments.AlifbahFragment;
import com.jeeb.farsialifba.fragments.NumberFragment;

/**
 * Created by Jeeb on 11/9/2016.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int mTabs;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        mTabs = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new AlifbahFragment();
                return fragment;
            case 1:
                fragment = new NumberFragment();
                return fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabs;
    }
}
