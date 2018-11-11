package com.chen.baseapplication.handelrThread;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.chen.baseapplication.R;

public class HandlerThreadActivity extends Activity {

    private Handler mHandler1;
    private Handler mHandler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);
        Thread1 mThread1 = new Thread1();
        mThread1.start();
        Thread2 mThread2 = new Thread2();
        mThread2.start();

    }

    class Thread1 extends Thread{

        @Override
        public void run(){
            super.run();
            Looper.prepare();
            mHandler1 = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    Log.e(HandlerThreadActivity.this.getPackageCodePath(),"Hander2 message");
                    return false;
                }
            });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                mHandler2.sendEmptyMessage(1);
            Looper.loop();
        }

    }

    class Thread2 extends Thread{

        @Override
        public void run(){
            super.run();
            Looper.prepare();
            mHandler2 = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message message) {
                    Log.e(HandlerThreadActivity.this.getPackageCodePath(),"Hander1 message");
                    return false;
                }
            });

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                mHandler1.sendEmptyMessage(1);
            Looper.loop();
        }

    }
}
