package com.example.huo.myappgit.adapter;


import android.app.Fragment;
import android.app.FragmentManager;

import com.example.huo.myappgit.base.FragmentPagerAdapter;
import com.example.huo.myappgit.ui.fragment.viewpager.PagerFragment;
import com.example.huo.myappgit.ui.fragment.viewpager.PagerFragment1;
import com.example.huo.myappgit.ui.fragment.viewpager.PagerFragment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huo on 30/06/16.
 */

public class LoadAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments = new ArrayList<>();

    public LoadAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PagerFragment();
                break;
            case 1:
                fragment = new PagerFragment1();
                break;
            case 2:
                fragment = new PagerFragment2();
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
