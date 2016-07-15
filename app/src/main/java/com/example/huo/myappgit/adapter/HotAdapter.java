package com.example.huo.myappgit.adapter;

import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.huo.myappgit.Entity.GitHubItemEntity;
import com.example.huo.myappgit.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Random;

/**
 * Created by huo on 08/07/16.
 */

public class HotAdapter extends BaseQuickAdapter<GitHubItemEntity> {


    public HotAdapter(int layoutResId) {
        super(layoutResId, null);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GitHubItemEntity gitHubItemEntity) {
        baseViewHolder.setText(R.id.tv_name, gitHubItemEntity.getFull_name());
        baseViewHolder.setText(R.id.tv_description, gitHubItemEntity.getDescription());
        baseViewHolder.setText(R.id.tv_watchers, gitHubItemEntity.getWatchers());
        ImageView view = baseViewHolder.getView(R.id.iv_icon);
        ImageLoader.getInstance().displayImage(gitHubItemEntity.getOwner().getAvatar_url(), view);
    }

    @Override
    public void openLoadAnimation() {
        super.openLoadAnimation(new Random().nextInt(5)+1);
    }
}
