package com.chen.baseapplication.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/11/26.
 */

public class MyLinearLayout extends LinearLayout {

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = false;
        Log.i("test", "【组长】下达任务：" + TouchEventUtils.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = true;//这里返回true  自身ontouchEvent处理
        Log.w("test", "【组长】是否拦截任务：" + TouchEventUtils.actionToString(ev.getAction())
                + "，拦下来？" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = true;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("test", "【组长】完成任务：" + TouchEventUtils.actionToString(event.getAction())
                        + "，【员工】太差劲了，以后不再找你干活了，我自来搞定！是否解决："
                        + TouchEventUtils.canDoTask(relust));
                break;

            case MotionEvent.ACTION_MOVE:
                scrollTo(-(int) event.getX(), -(int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                if(getScaleX() >= 1){
                    setScaleX(0.1f);
                    setScaleY(0.1f);
                }else{
                    setScaleX(1);
                    setScaleY(1);
                }

                Log.e("test","【组长】结束任务"+ TouchEventUtils.actionToString(event.getAction()));
                break;
        }

        return relust;
    }
}
