package com.example.huo.myappgit.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import com.example.huo.myappgit.Entity.Language;
import com.example.huo.myappgit.base.FragmentPagerAdapter;
import com.example.huo.myappgit.ui.fragment.mainpager.HotFragment;

import java.util.List;

/**
 * Created by huo on 08/07/16.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private List<Language> mLanguages;

    public MainAdapter(FragmentManager fm, Context context) {
        super(fm);
        mLanguages = Language.getLanguages(context);
    }

    @Override
    public Fragment getItem(int position) {
        return HotFragment.newInstance(mLanguages.get(position).getPath());
    }

    @Override
    public int getCount() {
        return mLanguages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLanguages.get(position).getName();
    }
}
