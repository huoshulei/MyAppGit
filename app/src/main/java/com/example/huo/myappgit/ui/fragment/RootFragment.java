package com.example.huo.myappgit.ui.fragment;


import android.animation.ArgbEvaluator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.RootAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.ui.activity.MainActivity;
import com.example.huo.myappgit.ui.view.RoundImageView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.COLLECT;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.DEVELOPER;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.DRY;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.HOT;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.POPULAR;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.RECOMMEND;
import static com.example.huo.myappgit.ui.fragment.RootFragment.Type.SHARE;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class RootFragment extends BaseFragment {
    private static final String TAG = "RootFragment";
    @BindView(R.id.layout_main)
    View mView;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.dl_root)
    DrawerLayout mDlRoot;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private RootAdapter mAdapter;
    MainActivity mMainActivity;
    BaseFragment mFragment;
    Map<Type, BaseFragment> mFragmentMap = new HashMap<>();

    public RootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mAdapter = new RootAdapter(getFragmentManager());
        mFragment = MainFragment.newInstance(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        ButterKnife.bind(this, view);
        mNavigationView.setCheckedItem(R.id.github_hot_repo);
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbarLayout.setTitle("GitHub");
        mToolbar.inflateMenu(R.menu.menu_main);
        replace(HOT);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private FragmentTransaction getFragmentTransaction() {
        FragmentManager fm = getFragmentManager();
        return fm.beginTransaction();
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        mNavigationView.setNavigationItemSelectedListener(getListener());
        mToolbar.setOnMenuItemClickListener(getMenuListener());
        mToolbar.setNavigationOnClickListener(getIconListener());
        mDlRoot.addDrawerListener(getAnimListener());
        if (mNavigationView.getHeaderCount() > 0) {
            View headerView = mNavigationView.getHeaderView(0);
            RoundImageView imageView = (RoundImageView) headerView.findViewById(R.id.rv_ss);
            imageView.setOnClickListener(getL());
        }
    }

    @NonNull
    private DrawerLayout.SimpleDrawerListener getAnimListener() {
        return new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mNavigationView.setBackgroundColor((Integer) getArgbEvaluator().evaluate
                        (slideOffset,
                        0x1826ca78, 0x8826ca78));
                float vX = 1f - 0.6f * slideOffset;
                float vY = 1f - 0.2f * slideOffset;
                mView.setScaleX(vX);
                mView.setScaleY(vY);
                mView.setTranslationX(mNavigationView.getWidth() * slideOffset * (1 - vX) / 90 *
                        (90 - 25));
                mView.setRotationY(-25 * slideOffset);
                mView.setRotation(mView.getWidth());
//                mView.setTranslationX(mNavigationView.getWidth() * slideOffset);
            }
        };
    }

    @NonNull
    private View.OnClickListener getIconListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDlRoot.openDrawer(mNavigationView);
                mMainActivity.showToast("顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶顶");
            }
        };
    }

    @NonNull
    private Toolbar.OnMenuItemClickListener getMenuListener() {
        return new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mMainActivity.showToast(item.getTitle().toString());
                switch (item.getItemId()) {
                    case R.id.action_share:
                        return true;
                    case R.id.action_kepp:
                        return true;
                    case R.id.action_settings:
                        return true;
                }
                return false;
            }
        };
    }

    @NonNull
    private View.OnClickListener getL() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDlRoot.closeDrawer(mNavigationView);
                mMainActivity.showToast("啊哈");
            }
        };
    }

    @NonNull
    private NavigationView.OnNavigationItemSelectedListener getListener() {
        return new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDlRoot.closeDrawer(mNavigationView);
                mMainActivity.showToast(item.getTitle().toString());
                switch (item.getItemId()) {
                    case R.id.github_hot_repo:
                        replace(HOT);
                        return true;
                    case R.id.github_hot_coder:
                        replace(DEVELOPER);
                        return true;
                    case R.id.github_trend:
                        replace(POPULAR);
                        return true;
                    case R.id.arsenal_my_repo:
                        replace(COLLECT);
                        return true;
                    case R.id.arsenal_recommend:
                        replace(RECOMMEND);
                        return true;
                    case R.id.tips_daily:
                        replace(DRY);
                        return true;
                    case R.id.tips_share:
                        replace(SHARE);
                        return true;
                }
                Log.d(TAG, "onNavigationItemSelected:>>>>> " + item.getItemId());
                return false;
            }
        };
    }

    private void replace(Type type) {
        getFragmentTransaction()
                .replace(R.id.fl_root, getFragment(type))
                .commit();
    }

    private BaseFragment getFragment(Type type) {
        BaseFragment fragment;
        if (mFragmentMap.containsKey(type)) {
            fragment = mFragmentMap.get(type);
        } else {
            fragment = getBaseFragment(type);
            mFragmentMap.put(type, fragment);
        }
        return fragment;
    }

    private BaseFragment getBaseFragment(Type type) {
        switch (type) {
            case HOT:
                return MainFragment.newInstance(0);
            case DEVELOPER:
                return MainFragment.newInstance(0);
            case POPULAR:
                return MainFragment.newInstance(0);
            case COLLECT:
                return MainFragment.newInstance(0);
            case RECOMMEND:
                return MainFragment.newInstance(0);
            case DRY:
                return MainFragment.newInstance(0);
            case SHARE:
                return MainFragment.newInstance(0);
        }
        return mFragment;
    }

    enum Type {
        /**
         * 热门
         */
        HOT,
        /**
         * 开发者
         */
        DEVELOPER,
        /**
         * 流行
         */
        POPULAR,
        /**
         * 收藏
         */
        COLLECT,
        /**
         * 推荐
         */
        RECOMMEND,
        /**
         * 干货
         */
        DRY,
        /**
         * 分享
         */
        SHARE,
    }
}
