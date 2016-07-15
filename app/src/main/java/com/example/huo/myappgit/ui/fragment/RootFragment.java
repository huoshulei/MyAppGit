package com.example.huo.myappgit.ui.fragment;


import android.animation.ArgbEvaluator;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.huo.myappgit.Entity.CurrentUser;
import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.RootAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.ui.activity.MainActivity;
import com.example.huo.myappgit.ui.view.RoundImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.res.Resources.*;
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
    View                    mView;
    @BindView(R.id.navigationView)
    NavigationView          mNavigationView;
    @BindView(R.id.dl_root)
    DrawerLayout            mDlRoot;
    @BindView(R.id.toolbar)
    Toolbar                 mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout            mAppBar;
    @BindView(R.id.fab)
    FloatingActionButton    mFab;
    private RootAdapter mAdapter;
    MainActivity mMainActivity;
    BaseFragment mFragment;
    Map<Type, BaseFragment> mFragmentMap = new HashMap<>();
    private ImageView mImageIcon;

    public RootFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivity = (MainActivity) getActivity();
        mAdapter = new RootAdapter(getFragmentManager());
        mFragment = MainFragment.newInstance(0);
        replace(HOT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_root, container, false);
        ButterKnife.bind(this, view);
        mNavigationView.setCheckedItem(R.id.github_hot_repo);//默认选中
        mToolbar.setNavigationIcon(R.mipmap.ic_launcher);
//        mToolbar.setLogo(R.mipmap.ic_launcher);
//        mToolbarLayout.setTitle("GitHub");
        mToolbar.inflateMenu(R.menu.menu_main);
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
        super.onViewCreated(view, savedInstanceState);
        mNavigationView.setNavigationItemSelectedListener(getListener());
        mToolbar.setOnMenuItemClickListener(getMenuListener());
        mToolbar.setNavigationOnClickListener(getIconListener());
        mDlRoot.addDrawerListener(getAnimListener());
        if (mNavigationView.getHeaderCount() > 0) {
            View headerView = mNavigationView.getHeaderView(0);
            mImageIcon = (ImageView) headerView.findViewById(R.id.rv_ss);
            mImageIcon.setOnClickListener(getL());
        }
        if (CurrentUser.isEmpty()) {
            mToolbarLayout.setTitle("GitHub");
            mImageIcon.setImageDrawable(createCircleImage(getResources().getDrawable(R.mipmap
                    .github, null)));
            return;
        }
        mToolbarLayout.setTitle(CurrentUser.getUser().getName());
        ImageLoader.getInstance().displayImage(CurrentUser.getUser().getAvatar(), mImageIcon);
    }


    @NonNull
    private DrawerLayout.SimpleDrawerListener getAnimListener() {
        return new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                mNavigationView.setBackgroundColor((Integer) getArgbEvaluator().evaluate
                        (slideOffset, 0x1826ca78, 0x8826ca78));
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
                if (CurrentUser.isEmpty())
                    getFragmentTransaction().replace(R.id.fl_main, new LandFragment()).commit();
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
                switch (item.getItemId()) {
                    case R.id.github_hot_repo:
                        replace(HOT);
                        return true;
                    case R.id.github_hot_coder:
                        mMainActivity.showToast(item.getTitle().toString());
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


    private Drawable createCircleImage(Drawable drawable) {
        Bitmap      source = ((BitmapDrawable) drawable).getBitmap();
        final Paint paint  = new Paint();
        paint.setAntiAlias(true);
        int    width  = source.getWidth();
        int    height = source.getHeight();
        int    min    = Math.min(width, height);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        drawable = new BitmapDrawable(getResources(), target);
        return drawable;
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
