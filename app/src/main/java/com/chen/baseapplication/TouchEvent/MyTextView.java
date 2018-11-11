package com.chen.baseapplication.TouchEvent;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/11/26.
 */

public class MyTextView extends TextView {


    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("test", "【员工】下达任务：" + TouchEventUtils.actionToString(event.getAction())
                + "，我没手下了，唉~自己干吧");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = true;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("test", "【员工】完成任务：" + TouchEventUtils.actionToString(event.getAction())
                        + "，【员工】现在只能靠自己了！是否解决：" +TouchEventUtils.canDoTask(relust));
                break;

            case MotionEvent.ACTION_MOVE:
//                scrollTo(-(int) event.getX(), -(int) event.getY());
                break;

            case MotionEvent.ACTION_UP:
                Log.e("test","【员工】结束任务"+ TouchEventUtils.actionToString(event.getAction()));
                break;
        }
        return relust;
    }
}
