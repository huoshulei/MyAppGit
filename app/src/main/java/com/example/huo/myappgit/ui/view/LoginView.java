package com.example.huo.myappgit.ui.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by huo on 06/07/16.
 */

public interface LoginView extends MvpView {

    // 显示进度条
    boolean showProgress();

    // 重置webView
    void resetWeb();

    void showMessage(String msg);

    // 导航至主页面
    void navigateToMain();
}
