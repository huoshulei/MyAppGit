package com.example.huo.myappgit.ui.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.MvpFragmentV7;
import com.example.huo.myappgit.http.GitHubApi;
import com.example.huo.myappgit.http.GitHubHttp;
import com.example.huo.myappgit.presentor.LoginPresentor;
import com.example.huo.myappgit.ui.activity.MainActivity;
import com.example.huo.myappgit.ui.view.LoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LandFragment extends MvpFragmentV7<LoginView, LoginPresentor> implements LoginView {
    private static final String TAG = "LandFragment";

    @BindView(R.id.wv_land)
    WebView mWvLand;
    private final GitHubHttp mHubHttp;
    @BindView(R.id.giv_gif)
    GifImageView mGivGif;
    MainActivity mMainActivity;

    public LandFragment() {
        mHubHttp = GitHubHttp.getGitHubHttp();
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
        View view = inflater.inflate(R.layout.fragment_land, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        CookieManager instance = CookieManager.getInstance();
//        instance.removeSessionCookie();
        initWabView();
    }

    private void initWabView() {
        mWvLand.loadUrl(GitHubApi.AUTH_URL);
        mWvLand.setFocusable(true);
        mWvLand.setFocusableInTouchMode(true);
        mWvLand.setWebChromeClient(getClient());
        mWvLand.setWebViewClient(getWebViewClient());
    }

    @NonNull
    private WebViewClient getWebViewClient() {
        return new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (GitHubApi.GITHUB_BACK.equals(uri.getScheme())) {
                    String code = uri.getQueryParameter("code");

                    getPresenter().login(code);
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        };
    }

    @NonNull
    private WebChromeClient getClient() {
        return new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mGivGif.setVisibility(View.GONE);
                    mWvLand.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, newProgress);
            }
        };
    }

    @Override
    public LoginPresentor createPresenter() {
        return new LoginPresentor();
    }

    @Override
    public boolean showProgress() {
        mGivGif.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public void resetWeb() {
        initWabView();
    }

    @Override
    public void showMessage(String msg) {
        mMainActivity.showToast(msg);
    }

    @Override
    public void navigateToMain() {
        FragmentManager     manager             = getFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main, new RootFragment())
                .commit();
    }
}
