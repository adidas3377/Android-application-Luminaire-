
package com.example.lamp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    private Fragment[] fragmentPages;

    public MyFragmentPageAdapter(FragmentManager fm, Fragment[] fragmentPages) {
        super(fm);
        this.fragmentPages = fragmentPages;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentPages[position];
    }

    @Override
    public int getCount() {
        return fragmentPages.length;
    }
}
