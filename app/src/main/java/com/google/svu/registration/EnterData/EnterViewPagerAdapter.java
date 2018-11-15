package com.google.svu.registration.EnterData;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class EnterViewPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public EnterViewPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }
}
