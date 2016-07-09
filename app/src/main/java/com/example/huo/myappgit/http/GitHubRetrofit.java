package com.example.huo.myappgit.http;

import com.example.huo.myappgit.Entity.BaseGitHubEntity;
import com.example.huo.myappgit.Entity.GitHubItemEntity;
import com.example.huo.myappgit.base.BaseRetrofit;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huo on 09/07/16.
 */

public abstract class GitHubRetrofit<T> extends BaseRetrofit<BaseGitHubEntity<List<T>>, T> {
    String path;
    int    page;

    public GitHubRetrofit() {
        super();
    }


    public void getData(String path, int page) {
        this.path = "language:" + path;
        this.page = page;
        setBaseEntityFunc(new GitHubHotFunc());
        getData();
    }

    public class GitHubHotFunc extends BaseEntityFunc {
        @Override
        public List<T> call(BaseGitHubEntity<List<T>> tBaseGitHubEntity) {
            return tBaseGitHubEntity.getItems();
        }
    }
}
