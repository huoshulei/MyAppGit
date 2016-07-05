package com.example.huo.myappgit.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.huo.myappgit.R;
import com.example.huo.myappgit.util.Utils;


/**
 * Created by huo on 02/07/16.
 */

public class RoundView extends View {
    int color = Color.parseColor("#000000");//背景颜色
    int height = Utils.dpToPx(1080, getResources());//高度
    int wight = Utils.dpToPx(1080, getResources());//宽度
    int space = Utils.dpToPx(1, getResources()); //画笔宽度
    int padding = Utils.dpToPx(10, getResources());
    float startX = 0;
    float delta = 10f;
    Paint mPaint;
    private Drawable mDrawable;

    public RoundView(Context context) {
        super(context);
    }

    public RoundView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.round_view, 0, 0);
        height = array.getDimensionPixelOffset(R.styleable.round_view_height, height);
        wight = array.getDimensionPixelSize(R.styleable.round_view_wight, wight);
        color = array.getColor(R.styleable.round_view_background_color, color);
        array.recycle();//回收
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        mPaint.setColor(color);
//        mPaint.setStrokeWidth(space);
        mDrawable = getBackground();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Bitmap bitmap=Bitmap.createBitmap(wight,height, Bitmap.Config.RGB_565);
//        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.tablet);
//        canvas.setBitmap(bitmap);

        canvas.drawRect(0, 0, wight, height, mPaint);
//        wight=bitmap.getWidth();
//        height=bitmap.getHeight();
        int radius = (wight <= height) ? wight / 2  : height / 2 ;
//        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(radius);
        mPaint.setAntiAlias(true);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawCircle(wight / 2, height / 2, radius, mPaint);


//        canvas.drawBitmap(bitmap,0,0,mPaint);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }


    public void setHeight(int height) {
        this.height = height;
    }

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getDelta() {
        return delta;
    }

    public void setDelta(float delta) {
        this.delta = delta;
    }
}
