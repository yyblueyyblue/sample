package com.chen.baseapplication.jni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chen.baseapplication.R;

public class JNIActivity extends Activity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jni);
        textView = (TextView)findViewById(R.id.tv_jni);
        String sr = "Version="+JNISample.getVersionInfo()+"\n"
                +"Func= "+JNISample.getFunc(2,4)+"\n"
                +"next= "+JNISample.getNext()+"\n"
                +"第二个JNI= "+JNISample.getSecondInfo()+"\n"
                +"第二个Max= "+JNISample.getSecondMax(10,20)+"\n";
        textView.setText(sr);
        findViewById(R.id.btn_jni).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JNIActivity.this,JNISample.getCount(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
}
