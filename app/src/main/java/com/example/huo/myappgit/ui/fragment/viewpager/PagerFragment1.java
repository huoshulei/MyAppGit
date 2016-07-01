package com.example.huo.myappgit.ui.fragment.viewpager;


import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseFragment;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class PagerFragment1 extends BaseFragment {


    public PagerFragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fragmentManager=getFragmentManager();
    }
}
