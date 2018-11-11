package com.chen.baseapplication.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chen.baseapplication.R;

public class ServiceActivity extends Activity implements View.OnClickListener{

    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevice);
        findViewById(R.id.btn_start_service).setOnClickListener(this);
        findViewById(R.id.btn_stop_service).setOnClickListener(this);
        findViewById(R.id.btn_bind_service).setOnClickListener(this);
        findViewById(R.id.btn_unbind_service).setOnClickListener(this);
        findViewById(R.id.btn_ibinder).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_service:
//                startService(new Intent(ServiceActivity.this,MyService.class));//显性
                Intent intent = new Intent();
                intent.setAction("com.chen.baseapplication.service.myservice");
                startService(intent);
                break;
            case R.id.btn_stop_service:
//                stopService(new Intent(ServiceActivity.this,MyService.class));
                Intent stopIntent = new Intent();
                stopIntent.setAction("com.chen.baseapplication.service.myservice");
                stopService(stopIntent);
                break;
            case R.id.btn_bind_service:
                //构建绑定服务的Intent对象
                Intent bindIntent = new Intent(this, MyService.class);
                //调用bindService()方法,以此停止服务
                bindService(bindIntent,conn,BIND_AUTO_CREATE);
                //参数说明
                //第一个参数:Intent对象
                //第二个参数:上面创建的Serviceconnection实例
                //第三个参数:标志位
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                break;
            case R.id.btn_unbind_service:
                //调用unbindService()解绑服务
                //参数是上面创建的Serviceconnection实例
                if(myService != null){
                    myService = null;
                    unbindService(conn);
                }
                break;
            case R.id.btn_ibinder:
                if (myService != null) {
                    Toast.makeText(this,"binder引用："+myService.getServiceName(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"暂无绑定服务",Toast.LENGTH_LONG).show();
                }
                break;
            default:

                break;
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            //在Activity调用Service类的方法
            myBinder.service_connect_Activity();

            myService = myBinder.getService();

        }

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.w("ServiceActivity","---------onServiceDeisconnected-------------");
            if(myService != null)
            myService = null;
        }
    };
}
