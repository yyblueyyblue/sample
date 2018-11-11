package com.chen.baseapplication.canvas;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/12/26.
 */

public class CanvasDrawView extends View {

    private Paint backPaint;
    private Paint paintCircle;
    private int mWidth;
    private int mHeight;

    public CanvasDrawView(Context context) {
        super(context);
        init();
    }

    public CanvasDrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

//    @TargetApi(19 <= Build.VERSION.SDK_INT)
//    // using @TargeApi instead of @SuppressLint("NewApi")
//    @SuppressWarnings("deprecation")
//    public CanvasDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    void init(){
        backPaint = new Paint();
        backPaint.setColor(Color.BLUE);
        backPaint.setAntiAlias(true);

        paintCircle = new Paint();
        paintCircle.setColor(Color.BLACK);

//        mWidth = getWidth();
//        mHeight = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();
        canvas.drawCircle(mWidth/2,mHeight/2,100,backPaint);
        canvas.save();
        for(int i = 0 ; i< 12 ; i++){
            canvas.rotate(30,mWidth/2,mHeight/2);
            canvas.drawLine(mWidth/2,
                            mHeight/2,
                            mWidth/2+220,
                            mHeight/2,
                            paintCircle);
        }
        canvas.restore();

    }

}
