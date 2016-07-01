package com.example.huo.myappgit.ui.fragment.viewpager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseFragment;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class PagerFragment2 extends BaseFragment {


    public PagerFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_fragment2, container, false);
    }

}
