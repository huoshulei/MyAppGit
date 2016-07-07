package com.example.huo.myappgit.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseActivity;
import com.example.huo.myappgit.ui.fragment.LoadFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        FragmentManager     fm          = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new LoadFragment());
        transaction.commit();
    }


}
