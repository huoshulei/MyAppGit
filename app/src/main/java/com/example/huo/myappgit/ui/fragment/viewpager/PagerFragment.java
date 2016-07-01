package com.example.huo.myappgit.ui.fragment.viewpager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class PagerFragment extends BaseFragment {


    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.imageView)
    ImageView mImageView;
    Animation mAnimation1;
    Animation mAnimation2;

    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mImageView.setVisibility(View.INVISIBLE);
        mImageView2.setVisibility(View.INVISIBLE);
        mAnimation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.scale);
        mAnimation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
        showAnim();
    }

    public void showAnim() {
        if (mImageView.getVisibility() == View.INVISIBLE) {
            mImageView.setVisibility(View.VISIBLE);
            mImageView.setAnimation(mAnimation1);
        }
        if (mImageView2.getVisibility() == View.INVISIBLE) {
            mImageView2.setVisibility(View.VISIBLE);
            mImageView2.setAnimation(mAnimation2);
        }
    }
}
