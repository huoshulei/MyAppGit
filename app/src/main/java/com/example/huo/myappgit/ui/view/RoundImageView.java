package com.example.huo.myappgit.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.example.huo.myappgit.R;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by huo on 03/07/16.
 */

public class RoundImageView extends ImageView {


    public RoundImageView(Context context) {
        super(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        setImageDrawable(createCircleImage(getDrawable()));
        super.onDraw(canvas);
//        if (mBitmap == null) {
//            return; // couldn't resolve the URI
//        }
//
//        if (mIntrinsicWidth == 0 || mIntrinsicHeight == 0) {
//            return;     // nothing to draw (empty bounds)
//        }
//        int min = Math.min(mIntrinsicWidth, mIntrinsicHeight);
//        mBitmap = Bitmap.createScaledBitmap(mBitmap, min, min, false);
//        if (mMatrix == null && mPaddingTop == 0 && mPaddingLeft == 0) {
//            canvas.drawBitmap(createCircleImage(mBitmap, min), 0, 0, null);
////            mDrawable.draw(canvas);
//        } else {
//            int saveCount = canvas.getSaveCount();
//            canvas.save();
//
//            if (getCropToPadding()) {
//                final int scrollX = mScrollX;
//                final int scrollY = mScrollY;
//                canvas.clipRect(scrollX + mPaddingLeft, scrollY + mPaddingTop,
//                        scrollX + mRight - mLeft - mPaddingRight,
//                        scrollY + mBottom - mTop - mPaddingBottom);
//            }
//
//            canvas.translate(mPaddingLeft, mPaddingTop);
//
//            if (mMatrix != null) {
//                canvas.concat(mMatrix);
//            }
////            mDrawable.draw(canvas);
//            canvas.drawBitmap(mBitmap,0,0,null);
//            canvas.restoreToCount(saveCount);
//        }
//        switch (type)
//        {
//            // 如果是TYPE_CIRCLE绘制圆形
//            case TYPE_CIRCLE:
//
//                int min = Math.min(mWidth, mHeight);
//                /**
//                 * 长度如果不一致，按小的值进行压缩
//                 */
//                mSrc = Bitmap.createScaledBitmap(mSrc, min, min, false);
//
//                canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
//                break;
//            case TYPE_ROUND:
//                canvas.drawBitmap(createRoundConerImage(mSrc), 0, 0, null);
//                break;
//
//        }

    }

    /**
     * 根据原图和变长绘制圆形图片
     *
     * @param drawable
     * @return
     */
    private Drawable createCircleImage(Drawable drawable) {
        Bitmap      source = ((BitmapDrawable) drawable).getBitmap();
        final Paint paint  = new Paint();
        paint.setAntiAlias(true);
        int    width  = source.getWidth();
        int    height = source.getHeight();
        int    min    = Math.min(width, height);
        Bitmap target = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
        /**
         * 产生一个同样大小的画布
         */
        Canvas canvas = new Canvas(target);
        /**
         * 首先绘制圆形
         */
        canvas.drawCircle(min / 2, min / 2, min / 2, paint);
        /**
         * 使用SRC_IN，参考上面的说明
         */
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        /**
         * 绘制图片
         */
        canvas.drawBitmap(source, 0, 0, paint);
        drawable = new BitmapDrawable(getResources(), target);
        return drawable;
    }

//    /**
//     * 根据原图添加圆角
//     *
//     * @param source
//     * @return
//     */
//    private Bitmap createRoundConerImage(Bitmap source) {
//        final Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(target);
//        RectF rect = new RectF(0, 0, source.getWidth(), source.getHeight());
//        canvas.drawRoundRect(rect, mRadius, mRadius, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(source, 0, 0, paint);
//        return target;
//    }
//
//    /**
//     * 计算控件的高度和宽度
//     */
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
//    {
//        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        /**
//         * 设置宽度
//         */
//        int specMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
//        {
//            mWidth = specSize;
//        } else
//        {
//            // 由图片决定的宽
//            int desireByImg = getPaddingLeft() + getPaddingRight()
//                    + mSrc.getWidth();
//            if (specMode == MeasureSpec.AT_MOST)// wrap_content
//            {
//                mWidth = Math.min(desireByImg, specSize);
//            } else
//
//                mWidth = desireByImg;
//        }
//
//        /***
//         * 设置高度
//         */
//
//        specMode = MeasureSpec.getMode(heightMeasureSpec);
//        specSize = MeasureSpec.getSize(heightMeasureSpec);
//        if (specMode == MeasureSpec.EXACTLY)// match_parent , accurate
//        {
//            mHeight = specSize;
//        } else
//        {
//            int desire = getPaddingTop() + getPaddingBottom()
//                    + mSrc.getHeight();
//
//            if (specMode == MeasureSpec.AT_MOST)// wrap_content
//            {
//                mHeight = Math.min(desire, specSize);
//            } else
//                mHeight = desire;
//        }
//
//        setMeasuredDimension(mWidth, mHeight);
//
//    }
}
