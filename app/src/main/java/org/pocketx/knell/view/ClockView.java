package org.pocketx.knell.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.pocketx.knell.R;
import org.pocketx.knell.utils.ConvertUtils;

/**
 * ClockView
 *
 * @author zhangchao
 */
public class ClockView extends View {

    private int mHourColor;
    private int mMinuteColor;
    private int mSecondColor;
    private int mEdgeColor;

    private Paint mPaint;

    private int mHour = 1;
    private int mMinute = 20;
    private int mSecond = 40;

    public ClockView(Context context) {
        this(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        /*
           变盘边缘的颜色
           时针、分针、秒针的颜色
        */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView, defStyleAttr, 0);
        mHourColor = typedArray.getColor(R.styleable.ClockView_hourColor, Color.BLACK);
        mMinuteColor = typedArray.getColor(R.styleable.ClockView_minuteColor, Color.BLUE);
        mSecondColor = typedArray.getColor(R.styleable.ClockView_secondColor, Color.RED);
        mEdgeColor = typedArray.getColor(R.styleable.ClockView_edgeColor, Color.BLACK);

        typedArray.recycle();

        mPaint = new Paint();
    }

    /**
     * TODO 宽度暂写死
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个○
        mPaint.setColor(mEdgeColor);
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        //辅助边框
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
        mPaint.setStrokeWidth(10);
        //变盘边框
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - 5, mPaint);

        //时针
        int hourWidth = 6;
        mPaint.setStrokeWidth(hourWidth);
        canvas.save();
        canvas.rotate(360 / 12 * mHour, getWidth() / 2, getHeight() / 2);
        canvas.drawRect((getWidth() - hourWidth) / 2, ConvertUtils.dp2px(100),
                (getWidth() + hourWidth) / 2, getHeight() / 2, mPaint);
        canvas.restore();

        //分针
        mPaint.setColor(mMinuteColor);
        int minuteWidth = 4;
        mPaint.setStrokeWidth(minuteWidth);
        canvas.save();
        canvas.rotate(360 / 60 * mMinute, getWidth() / 2, getHeight() / 2);
        canvas.drawRect((getWidth() - minuteWidth) / 2, ConvertUtils.dp2px(80),
                (getWidth() + minuteWidth) / 2, getHeight() / 2, mPaint);
        canvas.restore();

        //秒针
        mPaint.setColor(mSecondColor);
        int secondWidth = 2;
        mPaint.setStrokeWidth(secondWidth);
        canvas.save();
        canvas.rotate(360 / 60 * mSecond, getWidth() / 2, getHeight() / 2);
        canvas.drawRect((getWidth() - secondWidth) / 2, ConvertUtils.dp2px(60),
                (getWidth() + secondWidth) / 2, getHeight() / 2, mPaint);
        canvas.restore();

    }

    /**
     * 设置时间
     *
     * @param hour   时
     * @param minute 分
     * @param second 秒
     */
    public void setClockData(int hour, int minute, int second) {
        this.mHour = hour;
        this.mMinute = minute;
        this.mSecond = second;
        invalidate();
    }

}
