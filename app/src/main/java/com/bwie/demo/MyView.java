package com.bwie.demo;

import android.content.Context;


import android.graphics.Canvas;


import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Rect;

import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类描述:自定义基本图形的练习
 * 作者：陈文梦
 * 时间:2017/2/10 20:24
 * 邮箱:18310832074@163.com
 */

public
class
MyView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initpaint();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initpaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getWidth() / 2;
        mHeight = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //绘制颜色
        //canvas.drawColor(getResources().getColor(android.R.color.tab_indicator_text));

        /*//绘制一个点
        canvas.drawColor(Color.WHITE);
        canvas.drawPoint(300,300,mPaint);

        //绘制一组点，坐标位置由float数组指定
        canvas.drawPoints(new float[]{
                500,500,
                500,600,
                500,700
        },mPaint);*/

        /*//绘制直线
        canvas.drawLine(300, 300, 600, 500, mPaint);

        //绘制直线组
        canvas.drawLines(
                new float[]{
                        200, 200, 200, 400,
                        300, 300, 300, 500
                }, mPaint
        );*/

        //绘制矩形(3种方法)
        /*为什么会有Rect和RectF两种？两者有什么区别吗？
        答案当然是存在区别的，两者最大的区别就是精度不同，
        Rect是int(整形)的，而RectF是float(单精度浮点型)的。
        除了精度不同，两种提供的方法也稍微存在差别*/
        //第一种
        /*canvas.drawRect(500,500,700,600,mPaint);
        //第二种方法
        Rect rect=new Rect(300,500,700,600);
        canvas.drawRect(rect,mPaint);
        //第三种方法
        RectF rectF=new RectF(400,500,700,600);
        canvas.drawRect(rectF,mPaint);*/


        //绘制圆角矩形
       /* //第一种方法
        RectF rect=new RectF(300,200,500,700);
        canvas.drawRoundRect(rect,40,40,mPaint);
        //第二种方法(50,50,代表椭圆的两个半径)
        canvas.drawRoundRect(600,600,800,800,50,50,mPaint);*/

        //绘制背景为圆角矩形的椭圆(椭圆的两个半径为，两个宽的一半，两个高的一半)
        //如果大于一半则按照一半处理
        /*RectF rect=new RectF(300,200,500,700);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(rect,mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawRoundRect(rect,100,250,mPaint);*/

        //绘制椭圆矩形
        /*//第一种方法
        RectF rect=new RectF(300,300,500,800);
        canvas.drawOval(rect,mPaint);
        //第二种方法
        canvas.drawOval(300,300,500,800,mPaint);*/

        //绘制圆形
        canvas.drawCircle(600,600,200,mPaint);

        //绘制圆弧

    }

    public void initpaint() {
        //初始化画笔
        if (mPaint == null) {
            mPaint = new Paint();
            //画笔颜色
            mPaint.setColor(Color.BLACK);
            //画笔样式(Paint.Style.FILL实心)(Paint.Style.STROKE空心)
            mPaint.setStyle(Paint.Style.FILL);
            //抗锯齿
            mPaint.setAntiAlias(true);
            //画笔宽度
            mPaint.setStrokeWidth((float) 20.0);
        }
    }
}
