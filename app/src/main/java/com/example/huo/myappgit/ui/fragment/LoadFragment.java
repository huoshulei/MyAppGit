package com.example.huo.myappgit.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.LoadAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.ui.fragment.viewpager.PagerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class LoadFragment extends BaseFragment {
    private static final String TAG = "LoadFragment";

    LoadAdapter mAdapter;
    @BindView(R.id.vp_load)
    ViewPager mVpLoad;
    @BindView(R.id.iv_load0)
    ImageView mIvLoad0;
    @BindView(R.id.iv_load1)
    ImageView mIvLoad1;
    @BindView(R.id.iv_load2)
    ImageView mIvLoad2;
    ImageView[] icon;

    public LoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load, container, false);
        ButterKnife.bind(this, view);
        icon = new ImageView[]{mIvLoad0, mIvLoad1, mIvLoad2};
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mAdapter = new LoadAdapter(getFragmentManager());
        mVpLoad.setAdapter(mAdapter);
        mVpLoad.addOnPageChangeListener(getListener());
        PagerFragment fragment= (PagerFragment) mAdapter.getItem(0);
//        fragment.showAnim();
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (ImageView imageView : icon) {
                    Log.d(TAG, "onPageSelected: >>0");
//                    icon[0].setImageResource(R.mipmap.icon_false);
                    imageView.setImageResource(R.mipmap.icon_false);
                }
                icon[position].setImageResource(R.mipmap.icon_ture);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }
}
