package com.chen.baseapplication.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private boolean isQuit = false;

    private MyBinder mBinder = new MyBinder();

    public MyService() {

    }

    /**
     * 首次创建服务时，系统将调用此方法来执行一次性设置程序（在调用 onStartCommand() 或 onBind() 之前）。
     * 如果服务已在运行，则不会调用此方法。该方法只被调用一次
     */
    @Override
    public void onCreate() {
        Log.i(this.getClass().getName(),"    onCreate invoke  ");
        super.onCreate();
    }



    /**
     * 每次通过startService()方法启动Service时都会被回调。
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(this.getClass().getName(),"onStartCommand invoke");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isQuit) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("MyService", "       run " + System.currentTimeMillis());
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    public String getServiceName(){
        return "myService";
    }

    /**
     * 服务销毁时的回调
     */
    @Override
    public void onDestroy(){
        Log.i(this.getClass().getName(),"    onDestroy invoke  ");
        isQuit = true;
        super.onDestroy();
    }


    /**
     * 绑定服务时才会调用
     * 必须要实现的方法
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(this.getClass().getName(),"onBind invoke");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(this.getClass().getName(),"    执行了onUnbind()  ");
        return super.onUnbind(intent);
    }

    //新建一个子类继承自Binder类
    class MyBinder extends Binder {

        public void service_connect_Activity() {
            Log.i(this.getClass().getName(),"    Service关联了Activity,并在Activity执行了Service的方法  ");

        }

        public MyService getService(){
            return MyService.this;
        }

    }


}
