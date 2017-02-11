package com.bwie.demo;


import android.content.Context;


import android.content.res.TypedArray;
import android.graphics.Canvas;



import android.graphics.Color;

import android.graphics.Paint;

import android.util.AttributeSet;

import android.view.MotionEvent;
import android.view.View;


import android.widget.Toast;

/**
 * 类描述:自定义圆环嵌套
 * 作者：陈文梦
 * 时间:2017/2/11 08:53
 * 邮箱:18310832074@163.com
 */

public class Circle extends View {
    //声明画笔
    Paint opaint;
    Paint tpaint;
    Paint thpaint;
    String text;
    int ny;
    int wy;
    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        //在构造函数中来读取attrs中的属性
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.Circle);
        text = ta.getString(R.styleable.Circle_text);
        //第一个参数相当属性的id也就是属性的名字，第二个参数为默认值，它是在自定义布局中配置的属性不起作用时进行替换的
        //颜色返回int类型的值
        ny=ta.getColor(R.styleable.Circle_ny,Color.WHITE);
        wy=ta.getColor(R.styleable.Circle_wy,Color.YELLOW);
        //数值类型的要使用getDimension，返回float类型的值
        float textsize=ta.getDimension(R.styleable.Circle_textsize,1);

//记得此处要recycle();
        ta.recycle();
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//调用画笔
        Circl(canvas);
    }
    //重写onTouchEvent方法点击在不同位置，提示不同位置
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x;
        int y;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //得到点击坐标
                x= (int) event.getX();
                y=(int) event.getY();

                int x1=(x-getWidth()/2)*(x-getWidth()/2);
                int y1=(y-getHeight()/2)*(y-getHeight()/2);
                //判断点击位置是否在圆内
                if (x1+y1<70*70){
                    Toast.makeText(getContext(),"小圆内",Toast.LENGTH_SHORT).show();
                }else if (x1+y1<100*100&&x1+y1>70*70){
                    Toast.makeText(getContext(),"圆环内",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"圆环外",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    //创建一个画圆的方法
    public void Circl(Canvas canvas){

        //实例化第一只画笔
        opaint=new Paint();
        opaint.setColor(ny);
        opaint.setAntiAlias(true);
        opaint.setStyle(Paint.Style.FILL);
        opaint.setStrokeWidth(1);
        //画小圆
        canvas.drawCircle(getWidth()/2,getHeight()/2,70,opaint);

        //实例化第二只画笔
        tpaint=new Paint();
        tpaint.setColor(wy);
        tpaint.setAntiAlias(true);
        tpaint.setStyle(Paint.Style.STROKE);
        tpaint.setStrokeWidth(100);
        canvas.drawCircle(getWidth()/2,getHeight()/2,100,tpaint);

        //实例化第二只画笔
        thpaint=new Paint();
        thpaint.setColor(Color.BLACK);
        thpaint.setAntiAlias(true);
        thpaint.setStyle(Paint.Style.STROKE);
        thpaint.setStrokeWidth(1);

        float yh=thpaint.measureText("圆环");
        canvas.drawText("圆环",(getWidth()-yh)/2,getHeight()/2,thpaint);

    }
}
