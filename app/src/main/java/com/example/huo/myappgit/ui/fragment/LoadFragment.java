package com.example.huo.myappgit.ui.fragment;


import android.animation.ArgbEvaluator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huo.myappgit.Entity.CurrentUser;
import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.LoadAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.http.GitHubHttp;
import com.example.huo.myappgit.ui.activity.MainActivity;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.iv_phone_show)
    ImageView      mIvPhoneShow;
    @BindView(R.id.rl_phone)
    RelativeLayout mRlPhone;
    MainActivity mMainActivity;
    @BindView(R.id.tv_load)
    TextView mTvLoad;
    @BindView(R.id.tv_not_load)
    TextView mTvNotLoad;
    @BindColor(R.color.colorGreen)
    int green;
    @BindColor(R.color.colorRed)
    int red;
    @BindColor(R.color.colorYellow)
    int yellow;

    public LoadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_load, container, false);
        ButterKnife.bind(this, view);
        icon = new ImageView[]{mIvLoad0, mIvLoad1, mIvLoad2};
//        Typeface typeface = mMainActivity.getTypeface();
//        mTvLoad.setTypeface(typeface);
//        mTvNotLoad.setTypeface(typeface);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        mAdapter = new LoadAdapter(getFragmentManager());
        mVpLoad.setAdapter(mAdapter);
        mVpLoad.addOnPageChangeListener(getListener());
    }

    @NonNull
    private ViewPager.OnPageChangeListener getListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                if (position == 0) {
                    mVpLoad.setBackgroundColor((Integer) getArgbEvaluator().evaluate
                            (positionOffset, green, red));
                    float v = 0.2f + 0.8f * positionOffset;
                    mRlPhone.setScaleX(v);
                    mRlPhone.setScaleY(v);
                    float v1 = mMainActivity.getWidth() / 3 * (positionOffset - 1);
                    mRlPhone.setTranslationX(v1);
                    float v2 = mMainActivity.getHeight() / 4 * (1 - positionOffset);
                    mRlPhone.setTranslationY(v2);
                    mIvPhoneShow.setAlpha(positionOffset);
                    return;
                }
                if (position == 1) {
                    mVpLoad.setBackgroundColor((Integer) getArgbEvaluator().evaluate
                            (positionOffset, red, yellow));
                    mRlPhone.setTranslationX(-positionOffsetPixels);
                }

            }

            @Override
            public void onPageSelected(int position) {
                for (ImageView imageView : icon) {
                    imageView.setImageResource(R.mipmap.icon_false);
                }
                icon[position].setImageResource(R.mipmap.icon_ture);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    @OnClick({R.id.tv_load, R.id.tv_not_load})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_load:
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fl_main, new LandFragment());
                transaction.commit();
                break;
            case R.id.tv_not_load:
                FragmentManager managerroot = getFragmentManager();
                FragmentTransaction transactionroot = managerroot.beginTransaction();
                transactionroot.replace(R.id.fl_main, new RootFragment());
                transactionroot.commit();
                break;
        }
    }
}
