package com.example.huo.myappgit.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.MainAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class MainFragment extends BaseFragment {
    MainActivity mMainActivity;
    @BindView(R.id.tab_main)
    TabLayout mTabMain;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    private int mType;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("TYPE", type);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mType = getArguments().getInt("TYPE");
        mMainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVpMain.setAdapter(new MainAdapter(getFragmentManager(), getActivity()));
        mTabMain.setupWithViewPager(mVpMain);
    }
}
