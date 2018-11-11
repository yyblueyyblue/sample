package com.chen.baseapplication.test;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chen.baseapplication.R;
import com.chen.baseapplication.widget.PartyRoomLayout;

public class TestAddViewActivity extends AppCompatActivity {

    private InterfaceView imp1,imp2;
    private PartyRoomLayout room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add_view);
        room = (PartyRoomLayout)findViewById(R.id.ly_partroom);
        MySurfaceTexture mySurfaceTexture = new MySurfaceTexture(this);
        mySurfaceTexture.setBackgroundColor(Color.BLUE);
        imp1 = (InterfaceView)mySurfaceTexture;
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.btn_add:
                room.addView((MySurfaceTexture)imp1);
                Log.e("tag","getcount = "+room.getChildCount());
                break;

            case R.id.btn_rm:
                room.removeAllViews();
                break;
        }
    }
}
