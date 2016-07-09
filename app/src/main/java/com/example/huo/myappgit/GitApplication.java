package com.example.huo.myappgit;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by huo on 09/07/16.
 */

public class GitApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_avatar)
                .showImageOnLoading(R.mipmap.ic_avatar)
                .showImageOnFail(R.mipmap.ic_avatar)
                .displayer(new RoundedBitmapDisplayer(getResources().getDimensionPixelOffset(R
                        .dimen.dp_80)))
                .cacheInMemory(true) // 打开内存缓存
                .cacheOnDisk(true) // 打开硬盘缓存
                .resetViewBeforeLoading(true)// 在ImageView加载前清除它上面之前的图片
                .build();
        // ImageLoader的配置
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder
                (getApplicationContext())
                .memoryCacheSize(5 * 1024 * 1024)// 设置内存缓存为5M
                .defaultDisplayImageOptions(options)// 设置默认的显示选项
                .build();
        // 初始化ImageLoader
        ImageLoader.getInstance().init(config);
    }
}
