package com.chen.baseapplication.activitylanchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chen.baseapplication.R;

public class LaunchMode2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(this.getLocalClassName(),"onCreate()");
        setContentView(R.layout.activity_launch_mode2);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LaunchMode2Activity.this,LaunchMode3Activity.class));
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
        Log.e(this.getLocalClassName(),"onNewIntent2(Intent intent)");
        Toast.makeText(this,"onNewIntent2",Toast.LENGTH_LONG).show();
    }
}
