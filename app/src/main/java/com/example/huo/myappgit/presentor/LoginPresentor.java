package com.example.huo.myappgit.presentor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.huo.myappgit.Entity.CurrentUser;
import com.example.huo.myappgit.Entity.TokenEntity;
import com.example.huo.myappgit.Entity.User;
import com.example.huo.myappgit.http.GitHubApi;
import com.example.huo.myappgit.http.GitHubHttp;
import com.example.huo.myappgit.ui.view.LoginView;
import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huo on 06/07/16.
 */

public class LoginPresentor extends MvpNullObjectBasePresenter<LoginView> {
    private Call<TokenEntity> mEntityCall;
    private Call<User>        mUserCall;
    private static final String TAG = "LoginPresentor";

    public void login(String code) {
        getView().showProgress();
        if (mEntityCall == null)
            mEntityCall = GitHubHttp.getGitHubHttp().getOAuthToken(GitHubApi.GITHUB_ID, GitHubApi
                    .GITHUB_SECRET, code);
        mEntityCall.enqueue(getTokenCallback());
    }

    @NonNull
    private Callback<TokenEntity> getTokenCallback() {
        return new Callback<TokenEntity>() {
            @Override
            public void onResponse(Call<TokenEntity> call, Response<TokenEntity> response) {
                String access_token = response.body().getAccess_token();
                CurrentUser.setToken(access_token);
                if (mUserCall != null) mUserCall.cancel();
                mUserCall = GitHubHttp.getGitHubHttp().getUser();
                mUserCall.enqueue(getCallback());

            }

            @Override
            public void onFailure(Call<TokenEntity> call, Throwable t) {
                getView().showMessage("社么鬼" + t.getMessage());
                getView().showProgress();
                getView().resetWeb();

            }
        };
    }

    @NonNull
    private Callback<User> getCallback() {
        return new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User body = response.body();
                CurrentUser.setUser(body);
                getView().showMessage("登陆成功");
                getView().navigateToMain();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                CurrentUser.clear();
                getView().showMessage("死鬼" + t.getMessage());
                getView().showProgress();
                getView().resetWeb();
            }
        };
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (!retainInstance) {
            if (mEntityCall != null) mEntityCall.cancel();
            if (mUserCall != null) mUserCall.cancel();
        }
    }
}
