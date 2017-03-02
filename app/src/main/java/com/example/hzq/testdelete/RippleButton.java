package com.example.hzq.testdelete;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by hzq on 2017/2/17.
 */

public class RippleButton extends Button {
    RippleDrawable mRippleDrawable;

    public RippleButton(Context context) {
        this(context, null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRippleDrawable = new RippleDrawable();

//        直接调用setBackgroundDrawable(mRippleDrawable);也能实现涟漪效果，但不灵活
//        view中实现了这个回调所以参数写this就行
        mRippleDrawable.setCallback(this);
    }

    /**
     * 验证drawable
     *
     * @param who
     * @return
     */
    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == mRippleDrawable || super.verifyDrawable(who);
    }

    /**
     * 当尺寸改变时的刷新区域
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRippleDrawable.setBounds(0, 0, getWidth(), getHeight());
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
//    }

    /**
     * 计算组件宽度
     */

    private int measureWidth(int widthMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {//精确模式
            result = specSize;
        } else {
            result = 5;//最大尺寸模式，getDefaultWidth方法需要我们根据控件实际需要自己实现
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 计算组件高度
     */
    private int measureHeight(int heightMeasureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {//精确模式
            result = specSize;
        } else {
            result = 5;//最大尺寸模式，getDefaultWidth方法需要我们根据控件实际需要自己实现
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //调用自己的drwa方法绘制到按钮的画布上
        mRippleDrawable.draw(canvas);
        //先绘制的在下面所以要显示文字就得把这句放下面
        super.onDraw(canvas);
        System.out.println("onDraw下宽高测量"+getWidth()+":::"+getMeasuredWidth());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mRippleDrawable.onRippleTouch(event, getMeasuredWidth());

        return super.onTouchEvent(event);
    }
}
