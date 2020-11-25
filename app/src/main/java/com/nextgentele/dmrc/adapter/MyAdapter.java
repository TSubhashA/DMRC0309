package com.nextgentele.dmrc.adapter;

import android.content.Context;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.nextgentele.dmrc.fragment.LoginIDFragment;
import com.nextgentele.dmrc.fragment.MPinFragment;

public class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LoginIDFragment loginIDFragment = new LoginIDFragment();
                return loginIDFragment;
            case 1:
                MPinFragment mPinFragment = new MPinFragment();
                return mPinFragment;

            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
