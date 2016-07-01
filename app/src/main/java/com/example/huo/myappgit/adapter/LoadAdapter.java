package com.example.huo.myappgit.adapter;


import android.app.Fragment;
import android.app.FragmentManager;

import com.example.huo.myappgit.base.FragmentPagerAdapter;

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

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
