package com.chen.baseapplication.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by Administrator on 2016/11/26.
 */

public class MyFrameLayout extends FrameLayout {

    public MyFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = false;//这里返回true 直接上一层
        Log.i("test", "【经理】下达任务：" + TouchEventUtils.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false;//这里返回true  自身ontouchEvent处理  false 下一级处理
        Log.w("test", "【经理】是否拦截任务："
                + TouchEventUtils.actionToString(ev.getAction()) + "，拦下来？" + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = true;//返回true 自己处理完成   false 上层onTouch处理
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("test", "【经理】完成任务：" + TouchEventUtils.actionToString(event.getAction()) + "，【组长】太差劲了，以后不再找你干活了，我自来搞定！是否解决：" + TouchEventUtils.canDoTask(relust));
                break;

            case MotionEvent.ACTION_MOVE:
                scrollTo(-(int) event.getX(), -(int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                Log.e("test","【经理】结束任务"+ TouchEventUtils.actionToString(event.getAction()));
                break;
        }


        return relust;
    }
}
