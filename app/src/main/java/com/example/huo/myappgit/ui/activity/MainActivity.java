package com.example.huo.myappgit.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseActivity;
import com.example.huo.myappgit.ui.fragment.LoadFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fl_main, new LoadFragment());
        transaction.commit();
    }
}
