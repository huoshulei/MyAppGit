package com.example.huo.myappgit.http;

import com.example.huo.myappgit.Entity.TokenEntity;
import com.example.huo.myappgit.Entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by huo on 06/07/16.
 */

public interface GitHubApi {
    String GITHUB_BACK   = "myappgit";
    String GITHUB_ID     = "e999960b2435bbf6cc65";
    String GITHUB_SECRET = "a09d955253741301568808bba41322ca57ab7ba4";
    String GITHUB_SCOPE  = "user,public_repo,repo";
    String AUTH_URL      = "https://github.com/login/oauth/authorize?client_id="
            + GITHUB_ID + "&scope=" + GITHUB_SCOPE;

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<TokenEntity> getOAuthToken(@Field("client_id") String client_id,
                                    @Field("client_secret") String client_secret,
                                    @Field("code") String code);

    @GET("user")
    Call<User> getUser();
}
