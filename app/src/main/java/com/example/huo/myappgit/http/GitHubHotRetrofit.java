package com.example.huo.myappgit.http;

import com.example.huo.myappgit.Entity.BaseGitHubEntity;
import com.example.huo.myappgit.Entity.GitHubItemEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huo on 09/07/16.
 */

public class GitHubHotRetrofit extends GitHubRetrofit<GitHubItemEntity> {
    public GitHubHotRetrofit() {
        super();
    }

    @Override
    protected Observable getObservable() {
        return mRetrofit.create(GitHubHotItem.class).getObservable(path, page);
    }
    interface GitHubHotItem {
        /**
         * @param query  请求类型
         * @param pageId 请求页码
         */
        @GET("search/repositories")
        Observable<BaseGitHubEntity<List<GitHubItemEntity>>> getObservable(@Query("q") String query,
                                                                           @Query("page") int pageId);

    }
}
