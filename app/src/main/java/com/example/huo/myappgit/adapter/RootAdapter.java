package com.example.huo.myappgit.adapter;

import android.app.Fragment;
import android.app.FragmentManager;

import com.example.huo.myappgit.base.FragmentPagerAdapter;
import com.example.huo.myappgit.ui.fragment.MainFragment;

/**
 * Created by huo on 04/07/16.
 */

public class RootAdapter extends FragmentPagerAdapter {
    public RootAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 7;
    }
}
