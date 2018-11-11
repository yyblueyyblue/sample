package com.chen.baseapplication.activitylanchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chen.baseapplication.R;

public class LaunchMode4Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getLocalClassName(),"onCreate()");
        setContentView(R.layout.activity_launch_mode4);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaunchMode4Activity.this,LaunchMode3Activity.class));
            }
        });
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.e(this.getLocalClassName(),"onReStart()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.e(this.getLocalClassName(),"OnStart()");
    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.e(this.getLocalClassName(),"onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.e(this.getLocalClassName(),"onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.e(this.getLocalClassName(),"onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e(this.getLocalClassName(),"onDestroy()");
    }

    @Override
    protected void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.e(this.getLocalClassName(),"onNewIntent(Intent intent)4");
        Toast.makeText(this,"onNewIntent4",Toast.LENGTH_LONG).show();
    }
}
