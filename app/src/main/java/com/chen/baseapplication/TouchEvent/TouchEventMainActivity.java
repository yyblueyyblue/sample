package com.chen.baseapplication.TouchEvent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.chen.baseapplication.R;

public class TouchEventMainActivity extends Activity {

    MyFrameLayout fly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event_main);
        fly = (MyFrameLayout) findViewById(R.id.frg_ly);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("test", "【老板】下达任务：" + TouchEventUtils.actionToString(ev.getAction()) + "，找个人帮我完成，任务往下分发。");

                break;

            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;

        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean relust = true;
//        fly.scrollTo((int)event.getX(),(int)event.getY());
//        fly.scrollBy((int)event.getX(),(int)event.getY());
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("test", "【老板】完成任务：" + TouchEventUtils.actionToString(event.getAction())
                        + "，【经理】太差劲了，以后不再找你干活了，我自来搞定！是否解决："
                        + TouchEventUtils.canDoTaskTop(relust));

                break;

            case MotionEvent.ACTION_MOVE:
                Log.v("tag","---------"+ event.getRawX());
                fly.setTranslationX(10);
                break;

            case MotionEvent.ACTION_UP:
                Log.e("test","【老板】结束任务"+ TouchEventUtils.actionToString(event.getAction()));
                break;
        }
        return relust;
    }
}
