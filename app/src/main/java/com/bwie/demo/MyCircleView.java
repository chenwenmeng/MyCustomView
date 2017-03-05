package com.bwie.demo;

import android.content.Context;



import android.content.res.TypedArray;
import android.graphics.Canvas;


import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Path;

import android.graphics.Region;
import android.util.AttributeSet;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import android.widget.Toast;

/**
 * 类描述:圆环嵌套
 * 作者：陈文梦
 * 时间:2017/2/11 12:48
 * 邮箱:18310832074@163.com
 */

public
class
MyCircleView extends View {

    private Paint mPaint1;
    private Paint mPaint2;
    private Path greatPath;
    private Path smallPath;
    private Region greatRegion;
    private Region smallRegion;
    private String text,mtoast,t1;
    private int mColor1;
    private int mColor2;
    private float aFloat;

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
        //获取自定义属性
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyCircleView);
        mColor1=typedArray.getColor(R.styleable.MyCircleView_mPaint1,Color.WHITE);
        mColor2=typedArray.getColor(R.styleable.MyCircleView_wmPaint2,Color.WHITE);
        aFloat=typedArray.getFloat(R.styleable.MyCircleView_radius1,1);
        text=typedArray.getString(R.styleable.MyCircleView_text);
        mtoast=typedArray.getString(R.styleable.MyCircleView_mToast);
        t1=typedArray.getString(R.styleable.MyCircleView_t1);
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public MyCircleView(Context context) {
        super(context);
        initView(context, null);
    }

    //初始化画笔和Region
    public void initView(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            mPaint1 = new Paint();
            mPaint2 = new Paint();
            greatRegion = new Region();
            smallRegion = new Region();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化Path
        greatPath = new Path();
        smallPath = new Path();

        // ▼将剪裁边界设置为视图大小
        Region globalRegion = new Region(-getWidth(), -getHeight(), getWidth(), getHeight());

        //给画笔1设置颜色
        mPaint1.setColor(mColor1);
        //给大圆路径设置参数
        greatPath.addCircle(getWidth() / 2, getHeight() / 2, 400, Path.Direction.CW);
        //将path设置进入Region
        greatRegion.setPath(greatPath, globalRegion);

        //给画笔2设置颜色
        mPaint2.setColor(mColor2);
        //给小圆路径设置参数
        smallPath.addCircle(getWidth() / 2, getHeight() / 2, 200, Path.Direction.CW);
        smallRegion.setPath(smallPath, globalRegion);

        //绘制大圆小圆
        canvas.drawPath(greatPath, mPaint1);
        canvas.drawPath(smallPath, mPaint2);

        //初始化绘制文本的画笔及参数
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) 40);
        //获取文本宽度
        float measureText = paint.measureText(text);
        //获取文本高度
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float ceil = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        //绘制文本
        canvas.drawText(text, (getWidth() / 2 - measureText / 2), (getHeight() / 2-ceil/2), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //获取手指触摸的X、Y坐标点
            int x = (int) event.getX();
            int y = (int) event.getY();
            //判断所在位置并吐司
            if (smallRegion.contains(x, y)) {

                Toast.makeText(getContext(), mtoast, Toast.LENGTH_SHORT).show();
            } else
            if (greatRegion.contains(x, y)) {

                Toast.makeText(getContext(), "圆环内", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(getContext(), "圆环外", Toast.LENGTH_SHORT).show();
            }
        }

        return true;
    }
}
