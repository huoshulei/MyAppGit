package com.example.huo.myappgit.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.ui.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class MainFragment extends BaseFragment {
    MainActivity mMainActivity;
//    @BindView(R.id.toolbar)
//    Toolbar mToolbar;
    @BindView(R.id.tab)
    TabLayout mTab;

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
        View view = inflater.inflate(R.layout.activity_scrolling, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view,savedInstanceState);
//        ButterKnife.bind(this, view);
////        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
////        mMainActivity.setSupportActionBar(toolbar);
//////            mTab.set
////        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//    }

}
