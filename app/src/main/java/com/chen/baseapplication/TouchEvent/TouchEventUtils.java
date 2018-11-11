package com.chen.baseapplication.TouchEvent;

import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/11/26.
 */

public class TouchEventUtils {
    public static String actionToString(int action){
        String result = null;
        switch(action){
            case MotionEvent.ACTION_DOWN:
                result = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                result = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                result = "ACTION_UP";
                break;
        }
        return result;
    }
    public static String canDoTask(boolean can){
        String result = null;
        if(can){
            result = "完美解决该任务！";
        }
        else{
            result = "这活搞不定，交给老大完成吧。";
        }
        return result;
    }
    public static String canDoTaskTop(boolean can){
        String result = null;
        if(can){
            result = "完美解决该任务！";
        }
        else{
            result = "这活搞不定，放弃该任务。";
        }
        return result;
    }
}
