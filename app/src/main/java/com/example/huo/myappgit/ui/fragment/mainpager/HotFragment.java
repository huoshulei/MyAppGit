package com.example.huo.myappgit.ui.fragment.mainpager;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.huo.myappgit.Entity.GitHubItemEntity;
import com.example.huo.myappgit.R;
import com.example.huo.myappgit.adapter.HotAdapter;
import com.example.huo.myappgit.base.BaseFragment;
import com.example.huo.myappgit.base.BaseRetrofit;
import com.example.huo.myappgit.http.GitHubHotRetrofit;
import com.example.huo.myappgit.ui.activity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class HotFragment extends BaseFragment {
    @BindView(R.id.rv_hot)
    RecyclerView mRvHot;
    private String path;
    HotAdapter mHotAdapter;
    private GitHubHotRetrofit mHotRetrofit;
    private static final String TAG = "HotFragment";
    int page = 1;
    MainActivity mMainActivity;

    public static HotFragment newInstance(String path) {

        Bundle args = new Bundle();
        args.putString("NAME", path);
        HotFragment fragment = new HotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public HotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        path = getArguments().getString("NAME", "java");
        mHotRetrofit = new GitHubHotRetrofit();
        getData(path, page);
        mMainActivity = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRvHot.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (mHotAdapter == null) mHotAdapter = new HotAdapter(R.layout.view_hot_item);
        mHotAdapter.openLoadAnimation();
        mRvHot.setAdapter(mHotAdapter);
        mHotRetrofit.setRxJavaListener(getRxJavaListener());
        mHotAdapter.openLoadMore(true);
        mHotAdapter.setOnLoadMoreListener(getRequestLoadMoreListener());
//        mHotRetrofit.setBaseEntityFunc(new GitHubHotFunc());
    }

    @Override
    public void onResume() {
        super.onResume();
//        getData(path, page);
        mHotAdapter.notifyDataSetChanged();
    }

    @NonNull
    private BaseQuickAdapter.RequestLoadMoreListener getRequestLoadMoreListener() {
        return new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                page++;
                getData(path, page);
//                mHotAdapter.notifyDataChangedAfterLoadMore(false);
            }
        };
    }

    @NonNull
    private BaseRetrofit.RxJavaListener getRxJavaListener() {
        return new BaseRetrofit.RxJavaListener() {
            @Override
            public void onNext(List t) {
                if (t.size() <= 0 || t == null) {
                    mMainActivity.showToast("已无更多可以加载");
                    mHotAdapter.openLoadMore(false);
                    mHotAdapter.notifyDataChangedAfterLoadMore(false);
                    return;
                }
                //数据类型转换错误
//                Log.d(TAG, "onNext: " + ((GitHubItemEntity) t.get(0)).getFull_name());
                mHotAdapter.addData(t);
                mHotAdapter.setPageSize(mHotAdapter.getPageSize() + t.size());
                mHotAdapter.notifyDataChangedAfterLoadMore(true);
            }

            @Override
            public void onError(Throwable e) {
                ((MainActivity) getActivity()).showToast("网络有点小问题");
            }
        };
    }

    public void getData(String path, int page) {
        mHotRetrofit.getData(path, page);
    }

}
