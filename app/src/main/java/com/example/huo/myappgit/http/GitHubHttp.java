package com.example.huo.myappgit.http;

import com.example.huo.myappgit.Entity.TokenEntity;
import com.example.huo.myappgit.Entity.User;
import com.example.huo.myappgit.Interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

/**
 * Created by huo on 06/07/16.
 */

public class GitHubHttp implements GitHubApi {
    String BASE_URL = "https://api.github.com/";
    static GitHubHttp mGitHubHttp;
    GitHubApi  mGitHubApi;

    public GitHubHttp() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .build();
        mGitHubApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubApi.class);
    }

    public static GitHubHttp getGitHubHttp() {
        if (mGitHubHttp == null)
            mGitHubHttp = new GitHubHttp();
        return mGitHubHttp;
    }

    @Override
    public Call<TokenEntity> getOAuthToken(@Field("client_id") String client_id, @Field
            ("client_secret") String client_secret, @Field("code") String code) {
        return mGitHubApi.getOAuthToken(client_id, client_secret, code);
    }

    @Override
    public Call<User> getUser() {
        return mGitHubApi.getUser();
    }
}
