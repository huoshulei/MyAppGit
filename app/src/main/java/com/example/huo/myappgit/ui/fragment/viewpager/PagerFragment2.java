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
public class PagerFragment2 extends BaseFragment {


    @BindView(R.id.ivBubble1)
    ImageView mIvBubble1;
    @BindView(R.id.ivBubble2)
    ImageView mIvBubble2;
    @BindView(R.id.ivBubble3)
    ImageView mIvBubble3;
    Animation mAnimation,mAnimation1,mAnimation2;

    public PagerFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager_fragment2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mIvBubble1.setVisibility(View.INVISIBLE);
        mIvBubble2.setVisibility(View.INVISIBLE);
        mIvBubble3.setVisibility(View.INVISIBLE);
        mAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.translate);
        mAnimation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.translate1);
        mAnimation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.translate2);
        showAnim();
    }

    private void showAnim() {
        if (mIvBubble1.getVisibility() == View.INVISIBLE) {
            mIvBubble1.setVisibility(View.VISIBLE);
            mIvBubble1.setAnimation(mAnimation);
        }
        if (mIvBubble2.getVisibility() == View.INVISIBLE) {
            mIvBubble2.setVisibility(View.VISIBLE);
            mIvBubble2.setAnimation(mAnimation1);
        }
        if (mIvBubble3.getVisibility() == View.INVISIBLE) {
            mIvBubble3.setVisibility(View.VISIBLE);
            mIvBubble3.setAnimation(mAnimation2);
        }
    }
}
