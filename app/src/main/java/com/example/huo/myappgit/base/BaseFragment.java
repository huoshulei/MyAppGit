package com.example.huo.myappgit.base;

import android.animation.ArgbEvaluator;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by huo on 30/06/16.
 */

public class BaseFragment extends Fragment {
    ArgbEvaluator argbEvaluator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ArgbEvaluator getArgbEvaluator() {
        if (argbEvaluator == null)
            argbEvaluator = new ArgbEvaluator();
        return argbEvaluator;
    }
}
