package com.bwie.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 *
 * 类描述:自定义小球
 * 作者：陈文梦
 * 时间:2016/12/26 09:24
 * 邮箱:18310832074@163.com
 */

public
class
BallView extends View {


    private int width;
    private int height;
    private boolean ball;

    public BallView(Context context) {
        this(context, null);
    }

    public BallView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //写逻辑的方法
    public BallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取当前控件宽度
        width = this.getWidth();
        //获取当前控件高度
        height = this.getHeight();
        //初始化让小球显示在中心位置
        X = width / 2;
        Y = height / 2;
    }

    float X;
    float Y;
    //圆的半径
    float r = 100;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(X, Y, r, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                float x = event.getX();
                float y = event.getY();
                ball = isBall(x, y);
                Toast.makeText(getContext(), "" + ball, Toast.LENGTH_SHORT).show();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ball) {
                    X = event.getX();
                    Y = event.getY();

                    postInvalidate();

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }


        return true;
    }

    /**
     * 计算点击是否在小球上
     *
     * @param x
     * @param y
     */
    private boolean isBall(float x, float y) {

        //圆的两点间的距离公式（即圆心（0，0）与圆上的某点p（x,y）的距离就是半径）
        double sqrt = Math.sqrt((x - X) * (x - X) + (y - Y) * (y - Y));
        Log.i("TAG", sqrt + "");
        if (sqrt <= r) {

            return true;
        } else {
            return false;
        }
    }
}