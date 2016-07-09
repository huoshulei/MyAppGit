package com.example.huo.myappgit.base;

import com.example.huo.myappgit.Entity.GitHubItemEntity;
import com.example.huo.myappgit.http.GitHubRetrofit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by huo on 06/07/16.
 */

public abstract class BaseRetrofit<T, V> {
    protected Retrofit mRetrofit;
    private final String BASE_URL = "http://api.github.com/";
    RxJavaListener mRxJavaListener;
    BaseEntityFunc mBaseEntityFunc;

    public BaseRetrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(5, TimeUnit.SECONDS);
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

    protected abstract Observable<T> getObservable();

    private void getData(Subscriber<List<V>> subscriber) {
        if (mBaseEntityFunc != null)
            getObservable()
                    .map(mBaseEntityFunc)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
                    .subscribe(subscriber);
    }

    protected void getData() {
        Subscriber<List<V>> subscriber = new Subscriber<List<V>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mRxJavaListener.onError(e);
            }

            @Override
            public void onNext(List<V> t) {

                mRxJavaListener.onNext(t);
            }
        };
        getData(subscriber);
    }

    public void setRxJavaListener(RxJavaListener rxJavaListener) {
        mRxJavaListener = rxJavaListener;
    }

    public void setBaseEntityFunc(BaseEntityFunc baseEntityFunc) {
        mBaseEntityFunc = baseEntityFunc;
    }

    public interface RxJavaListener {
        void onNext(List t);

        void onError(Throwable e);
    }

    public abstract class BaseEntityFunc implements Func1<T, List<V>> {
        @Override
        public abstract List<V> call(T t);
    }
}
