package com.example.huo.myappgit.base;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

/**
 * Created by huo on 30/06/16.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    public void initEvent() {

    }

    public void initData() {

    }

    public void initView() {
    }

    public Typeface getTypeface() {
        return Typeface.createFromAsset(getAssets(), "ttf/typeface.ttf");
    }

    public void showToast(String data) {
        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_SHORT).show();
    }

    public float getWidth() {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.widthPixels;
    }

    public float getHeight() {
        DisplayMetrics metrics = getDisplayMetrics();
        return metrics.heightPixels;
    }

    @NonNull
    private DisplayMetrics getDisplayMetrics() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }

    public void startActivity(Class<?> activity) {

        startActivity(activity, null, null);
    }

    public void startActivity(Class<?> activity, Bundle bundle) {
        startActivity(activity, bundle, null);
    }

    public void startActivity(Class<?> activity, Uri uri) {
        startActivity(activity, null, uri);
    }

    public void startActivity(Class<?> activity, Bundle bundle, Uri uri) {
        Intent intent = new Intent(this, activity);
        if (bundle != null)
            intent.putExtras(bundle);
        if (uri != null)
            intent.setData(uri);
        startActivity(intent);
    }

}
