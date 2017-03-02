package com.example.hzq.testdelete;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;

/**
 * Created by hzq on 2017/2/17.
 */

public class RippleDrawable extends Drawable {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mAlpha = 55;
    private int mRippleColor = 0;
    private int mRippleWidth;
    //    private Bitmap bitmap;
    private float mRipplePointX, mRipplePointy, mRippleRadius;
Handler h=new Handler(new Handler.Callback() {
    @Override
    public boolean handleMessage(Message msg) {

        if (msg.what==66){
            if (mRippleRadius>mRippleWidth) {
                mRippleRadius = 0;
            }
            else{
            mRippleRadius += 25;

            h.sendEmptyMessageDelayed(66,30);}
            invalidateSelf();

        }
        return false;
    }
});
    public RippleDrawable() {
        //抗锯齿
        mPaint.setAntiAlias(true);
        //防抖动
        mPaint.setDither(true);
        setRippleColor(Color.RED);
//        this.bitmap=bitmap;
        //给背景设置滤镜第一个参数是要保留的颜色，第二个参数是要填充的颜色
//        setColorFilter(new LightingColorFilter(Color.RED,0x30300000));
    }

    public void setRippleColor(int color) {
        /**不建议直接画笔设置颜色mPaint.setColor(color);，如果传进来的color是有alpha的，
         * drawable的alpha和color的alpha不同，最终paint画出的颜色也会被影响
         */
        mRippleColor = color;
        onColorOrAlphaChanged();
    }

    @Override
    public void draw(Canvas canvas) {
//        canvas.drawColor(0xff00ff00);
//canvas.drawBitmap(bitmap,0,0,mPaint);
        canvas.drawCircle(mRipplePointX, mRipplePointy, mRippleRadius, mPaint);
    }

    /**
     * 当透明度不同时的处理方法
     */
    private void onColorOrAlphaChanged() {
        mPaint.setColor(mRippleColor);
        //Color.alpha(mRippleColor);或者下面的方法得到颜色透明度
        int malpha = mPaint.getAlpha();
        if (mAlpha != 255) {
            int realAlpha = (int) (malpha * (mAlpha / 255f));
            mPaint.setAlpha(realAlpha);
        }
    }

    public void onRippleTouch(MotionEvent event,int mRippleWidth) {
        this.mRippleWidth=mRippleWidth;
        System.out.println("传进来的宽度"+mRippleWidth);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                mRipplePointX = event.getX();
                mRipplePointy = event.getY();
                mRippleRadius += 25;
                invalidateSelf();
                break;
            case MotionEvent.ACTION_MOVE:
                mRippleRadius += 25;
                mRipplePointX = event.getX();
                mRipplePointy = event.getY();
                invalidateSelf();
                break;
            case MotionEvent.ACTION_DOWN:
                mRipplePointX = event.getX();
                mRipplePointy = event.getY();
                h.sendEmptyMessage(66);

                break;
            case MotionEvent.ACTION_CANCEL:
                mRippleRadius = 0;
                mRipplePointX = event.getX();
                mRipplePointy = event.getY();
                invalidateSelf();
                break;
        }
    }

    @Override
    public void setAlpha(int alpha) {
        //动态改变透明度
        mAlpha = alpha;
        onColorOrAlphaChanged();
    }

    @Override
    public int getAlpha() {
        return mAlpha;
    }

    /**
     * 设置图片的滤镜（背景是图片的时候用）
     *
     * @param colorFilter
     */
    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        ColorFilter mColorFilter = mPaint.getColorFilter();
        if (colorFilter != mColorFilter)
            mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        int alpha = mPaint.getAlpha();
        if (alpha == 255)
            return PixelFormat.OPAQUE;
        else if (alpha == 0)
            return PixelFormat.TRANSPARENT;
        else
            return PixelFormat.TRANSLUCENT;
    }
}
