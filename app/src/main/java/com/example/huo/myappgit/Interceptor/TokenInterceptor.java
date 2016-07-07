package com.example.huo.myappgit.Interceptor;

import android.content.ContentUris;

import com.example.huo.myappgit.Entity.CurrentUser;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huo on 06/07/16.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        if (CurrentUser.hasAccessToken())
            builder.header("Authorization", "token " + CurrentUser.getToken());
        Response proceed = chain.proceed(builder.build());
        if (proceed.isSuccessful())
            return proceed;
        if (proceed.code() == 401 || proceed.code() == 403) {
            throw new IOException("未经授权");
        } else {
            throw new IOException("返回码:" + proceed.code());
        }
    }
}
