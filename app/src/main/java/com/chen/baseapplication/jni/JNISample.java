package com.chen.baseapplication.jni;

import android.util.Log;

/**
 * Created by Administrator on 2017/11/25.
 */

public class JNISample {

    public static JNISample jniSample= null;

    public static JNISample getInstance(){
        if(jniSample == null){
            jniSample = new JNISample();
        }
        return jniSample;
    }

    public static void getStringFromJavaStatic() {
        Log.e("tag", "string from static method in java");
    }

    public String getStringForJava(){
        Log.e("tag","JNI 调用 方法");
        return "这个还用说吗 这个是java对象使用的";
    }

    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("nativetemp-lib");
    }
    public static native String getNext();

    public  static native String getVersionInfo();

    public static native String getCount();

    public static native int getFunc(int num1,int num2);

    public static native String getSecondInfo();

    public static native int getSecondMax(int number1,int number2);

}
