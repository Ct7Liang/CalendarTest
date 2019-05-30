package com.example.administrator.calendartest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.MonthView;

public class MyMonthView extends MonthView {

    private Paint mPaint = new Paint();

    public MyMonthView(Context context) {
        super(context);
    }

    /**
     * 绘制选中的日子
     * @param canvas    canvas
     * @param calendar  日历日历calendar
     * @param x         日历Card x起点坐标
     * @param y         日历Card y起点坐标
     * @param hasScheme hasScheme 非标记的日期
     * @return 返回true 则绘制onDrawScheme，因为这里背景色不是是互斥的，所以返回true
     */
    @Override
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
//        Log.i("ct7liang123", "onDrawSelected");
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(x+15, y+15, x+mItemWidth-8, y+mItemHeight-23, mPaint);
//        calendar.setSchemeColor(Color.parseColor("#FFFFFF"));
        return true;
    }

    /**
     * 绘制标记的事件日子
     * @param canvas   canvas
     * @param calendar 日历calendar
     * @param x        日历Card x起点坐标
     * @param y        日历Card y起点坐标
     */
    @Override
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
//        Log.i("ct7liang123", "onDrawScheme");
        if (!calendar.isCurrentDay()){
            Paint p = new Paint();
            p.setColor(Color.WHITE);
            canvas.drawCircle(x+mItemWidth-20, y+20, 25, p);
            p.setColor(calendar.getSchemeColor());
            p.setTextSize(25);
            canvas.drawText(calendar.getScheme(), x+mItemWidth-20-10, y+20+10, p);
        }
//        Log.i("ct7liang123", "x="+x+", y="+y + ", str=" + calendar.getScheme());
    }

    /**
     * 绘制文本
     * @param canvas     canvas
     * @param calendar   日历calendar
     * @param x          日历Card x起点坐标
     * @param y          日历Card y起点坐标
     * @param hasScheme  是否是标记的日期
     * @param isSelected 是否选中
     */
    @Override
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {

        mPaint.setTextSize(30);
        mPaint.setAntiAlias(true);

        if (calendar.isCurrentDay()){
            mPaint.setColor(Color.GRAY);
            canvas.drawRect(x+15, y+15, x+mItemWidth-8, y+mItemHeight-23, mPaint);
            mPaint.setColor(Color.parseColor("#FFFFFF"));

        }else if (calendar.isCurrentMonth()){
            if (isSelected){
                mPaint.setColor(Color.parseColor("#FFFFFF"));
            }else{
                mPaint.setColor(Color.parseColor("#000000"));
            }
        }else{
            mPaint.setColor(Color.parseColor("#CCCCCC"));
        }
        canvas.drawText(calendar.getDay()+"", x+mItemWidth/2-7, y+mItemHeight/2, mPaint);

        if (calendar.isCurrentDay()){
            if (hasScheme){
                mPaint.setColor(Color.WHITE);
                canvas.drawCircle(x+mItemWidth/2, y+mItemHeight-35, 5, mPaint);

                canvas.drawCircle(x+mItemWidth-20, y+20, 25, mPaint);

                mPaint.setColor(calendar.getSchemeColor());
                mPaint.setTextSize(25);
                canvas.drawText(calendar.getScheme(), x+mItemWidth-20-10, y+20+10, mPaint);
            }
        }else{
            if (hasScheme && isSelected){
                mPaint.setColor(Color.WHITE);
                canvas.drawCircle(x+mItemWidth/2, y+mItemHeight-35, 5, mPaint);
            }
            if (hasScheme && !isSelected){
                mPaint.setColor(Color.BLACK);
                canvas.drawCircle(x+mItemWidth/2, y+mItemHeight-35, 5, mPaint);
            }
        }
    }
}